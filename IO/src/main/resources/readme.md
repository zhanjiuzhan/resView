## File
描述文件本身的属性，用于获取和操作与磁盘文件关联的信息，如权限，时间，日起以及目录路径等。
```java
// 创建一个文件对象（Linux和window文件路径，/ 都会被正确解析，\ 注意需要转义才行）
File(String directoryPath)
File(String directoryPath, String filename)
File(File dirObj, String filename)
File(URI uriobj)
```

```java
// 文件的其它方法
void deleteOnExit()  // 当虚拟机终止时删除与调用对象关联的文件
long getFreeSpace()  // 返回在与调用对象关联的分区中，剩余存储空间的字节数
long getTotalSpace() // 返回在与调用对象关联的分区的存储容量
long getUsableSpace()// 返回在与对象关联的分区中，剩余可用存储空间的字节数
boolean isHidden()   // 如果调用文件是否隐藏
boolean setLastModified(long millisec)  // 设置调用文件的时间 毫秒1970/1/1到现在
boolean setReadOnly() // 将调用文件设置为只读
Path toPath()         // 将File对象转化为Path对象
String[] list()       // 如果是目录的话 返回目录下文件或目录列表
String[] list(FilenameFilter FFObj)  // 根据过滤条件返回文件列表
File[] listFiles()  // 返回目录文件对象
File[] listFiles(FilenameFilter FFObj)
File[] listFiles(FileFilter FObj)
```

## 字节流
InputStream
OutputStream

### InputStream
```java
int available()  // 返回当前可读取的输入字节数
void close()     // 关闭输入源 若继续进行读取 会产生IOException
void mark(int numBytes)  // 在输入流的当前位置放置标记，该标记在numBytes个字节之前一致都有效
boolean markSupported()  // 如果调用流支持mark()或reset() 就返回true
int read()   // 返回下一个可用字节数 当达到末尾时返回-1
int read(byte buffer[])  // 尝试读取buffer.length个字节到buffer中，并返回实际成功读取的字节数，如果达到文件末尾，返回-1
int read(byte buffer[], int offset, int numBytes) // 尝试读取numBytes个字节到buffer中，从buffer[offset]开始保存读取的字节，返回实际成功读取的字节数，如果达到文件末尾，返回-1
void reset()   // 将输入指针重置为前面设置的标记
long skip(long numBytes)   // 户略numBytes个字节的输入，返回实际户略的字节数
```

## 字符流
Reader
Writer

