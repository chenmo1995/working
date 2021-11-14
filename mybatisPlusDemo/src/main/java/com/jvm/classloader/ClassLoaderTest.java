package com.jvm.classloader;

import java.io.*;

/**
 * 场景一：
 * 将编译生成的.class文件全部copy到C:\Users\issuser\Desktop\test\com\jvm\classloader路径下，
 * 将classPath下的生成的Cat.class删除，运行查看控制台输出
 * 结论：Person由AppClassLoader加载，Cat由自定义的ClassLoaderTest类加载器加载。
 *
 * 场景二：
 * 将编译生成的.class文件全部copy到C:\Users\issuser\Desktop\test\com\jvm\classloader路径下，
 * 将classPath下的生成的Cat.class删除，在Cat的构造中调用new CatPerson()
 * 结论：子类加载器加载的类可以调用父类加载器加载的类(同一命名空间)Person由AppClassLoader加载，
 * Cat由自定义的ClassLoaderTest类加载器加载。
 *
 * 场景三：
 * 将编译生成的.class文件全部copy到C:\Users\issuser\Desktop\test\com\jvm\classloader路径下，
 * 将classPath下的生成的Cat.class删除，在Person的构造中调用new Cat()
 * 结论：父类加载器加载的类不可以调用子类加载器加载的类(不在同一命名空间)Person由AppClassLoader加载，
 * Cat由自定义的ClassLoaderTest类加载器加载。
 *
 * Exception in thread "main" java.lang.NoClassDefFoundError: com/jvm/classloader/Cat
 * 	at com.jvm.classloader.Person.<init>(Person.java:10)
 *
 *
 * @author fdn
 * @since 2021-10-29 16:07
 */
public class ClassLoaderTest extends ClassLoader{
    private String classLoaderName;
    private String baseUrl;

    //自定义findClass方法，只有在使用自定义累加器时，才会调用
    @Override
    public Class<?> findClass(String className){
        System.out.println("自定义findClass被调用...");
        String path = baseUrl + className.replace(".", "\\") + ".class";
        System.out.println("当前加载的类的全限定名是 ：" + path);
        byte data[] = findData(path);
        Class<?> calzz = defineClass(className, data, 0, data.length);
        return calzz;
    }

    public ClassLoaderTest(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public ClassLoaderTest(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    //设置一个路径，用来存放编译生成的.class文件；
    //该路径与默认的classPath不同，AppClassLoader无法加载该路径下的类，自定义类加载器可以加载该路径下的类
    private void setPath(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    //自定义findData 将.class文件解析成byte数组
    private byte[] findData(String className) {
        InputStream in = null;
        byte[] ch = null;
        ByteArrayOutputStream out = null;

        try {
            in = new FileInputStream(className);
            out = new ByteArrayOutputStream();
            int a = 0;
            while( -1 != (a = in.read())) {
                out.write(a);
            }
            ch = out.toByteArray();
            return ch;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return ch;
    }


    public static void main(String[] args) throws Exception {
        ClassLoaderTest loader1 = new ClassLoaderTest("loader1");
        loader1.setPath("C:\\Users\\issuser\\Desktop\\test\\");//设置自定义类加载器的加载路径
        //被类加载器加载后，得到Class对象
        Class<?> c1 = loader1.loadClass("com.jvm.classloader.Person");
        Object o1 = c1.newInstance();//实例化Person
        System.out.println(o1);

        ClassLoaderTest loader2 = new ClassLoaderTest("loader1");
        loader2.setPath("C:\\Users\\issuser\\Desktop\\test\\");
        Class<?> c2 = loader2.loadClass("com.jvm.classloader.Cat");
        Object o2 = c2.newInstance();//实例化Cat
        System.out.println(o2);
    }
}
