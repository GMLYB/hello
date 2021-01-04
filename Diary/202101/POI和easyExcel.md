## POI和easyExcel

### 1 使用场景

* 将用户信息导出为excel表格
* 将Excel表中的信息录入到网站数据库（习题上传），减轻网站录入量
* 开发中经常会涉及到excel表格的处理，导入excel到数据库中
* 操作excel目前使用最多的是 **Apache POI** 和 阿里巴巴的**easyExcel** 



### 2 POI

* POI，全称Apache POI，使用Java编写的免费开源的跨平台的Java API。 
* 是创建和维护操作各种符合 Office Open XML（OOXML）标准和微软的 OLE 2 复合文档格式（OLE2）的 Java API。
* 它可以使用 Java 读取和创建, 修改 MS Excel 文件. 而且, 还可以使用 Java 读取和创建 MS Word 和 MSPowerPoint 文件。



#### 2.1 结构

* HSSF：提供读写Microsoft **Excel（03版本，行数有限，约为65535行）**格式档案的功能
* XSSF：提供读写Microsoft Excel **OXXML（07版本，行数无限）**格式档案的功能
* HWPF：提供读写Microsoft **Word**格式档案的功能
* HSLF：提供读写Microsoft **PowerPoint**格式档案的功能
* HDGF：提供读写Microsoft **Visio**格式档案的功能



#### 2.2 Excel的主要对象

* 工作簿
* 工作表
* 行
* 列



#### 2.3 创建excel流程

* 创建一个工作簿
* 创建一个工作表
* 创建一个行（起始0）
* 创建一个单元格（列）
* 创建输出流（关闭）



##### （1）相关依赖

```xml
        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>3.9</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>3.9</version>
        </dependency>
```



##### （2）03版本的Excel创建代码示例

```java
    final String PATH = "E:\\IDEA项目\\December\\POITest\\src\\main\\";
    @Test
    public void test1() throws IOException {
        //1.创建一个工作簿
        Workbook workbook = new HSSFWorkbook();

        //2.创建一个工作表
        Sheet sheet = workbook.createSheet("这是Java创建的第一个Excel文件");

        //3.创建一个行
        Row row1 = sheet.createRow(0);

        //4.创建一个单元格（列）1 1
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue(666);

        //1 2
        Cell cell12 = row1.createCell(1);
        cell12.setCellValue("这是第一行第二列");

        Row row2 = sheet.createRow(1);
        //2 1
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("这是第二行第一列");

        //2 2
        Cell cell22 = row2.createCell(1);
        String time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(time);

        //5. 生成一张表
        FileOutputStream fos = new FileOutputStream(PATH + "03版本练习表.xls");

        workbook.write(fos);
        //关闭流
        fos.close();

        System.out.println("03文件生成完毕");

    }
```

##### （3）07版本与03版本的区别

```java
    //1.创建一个工作簿
    Workbook workbook = new HSSFWorkbook();//03版本
    Workbook workbook = new XSSFWorkbook();//07版本
    //5. 生成一张表
    FileOutputStream fos = new FileOutputStream(PATH + "03版本练习表.xls");
    FileOutputStream fos = new FileOutputStream(PATH + "07版本练习表.xlsx");
```

最后的效果图

<img src='data:img/jpg;base64,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'/>



#### 2.4 大量数据的批量写入

##### （1） 03版本的HSSF

优点：过程中写入缓存，不操作磁盘，最后一次性写入磁盘，速度快

缺点：最多只能处理65535行数据，否则会出现异常

```java
 @Test
    public void test3() throws IOException {

        long start = System.currentTimeMillis();

        //1.创建一个工作簿
        Workbook workbook = new HSSFWorkbook();

        //2.创建一个工作表
        Sheet sheet = workbook.createSheet("03大数据表");

        //3.创建一个行
        for (int i = 0; i < 65535; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < 10; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(j);
            }
        }
        System.out.println("over");

        //5. 生成一张表
        FileOutputStream fos = new FileOutputStream(PATH + "03版本大数据练习表.xls");

        workbook.write(fos);
        //关闭流
        fos.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);//1879
        System.out.println("03文件生成完毕");

    }
```



##### （2） 07版本的XSSF

优点：可以写入较大量的数据，如20万条

缺点：写数据速度非常慢，非常消耗内存，也会出现内存溢出，如100万条的时候

```java
 @Test
    public void test4() throws IOException {

        long start = System.currentTimeMillis();

        //1.创建一个工作簿
        Workbook workbook = new XSSFWorkbook();

        //2.创建一个工作表
        Sheet sheet = workbook.createSheet("07大数据表");

        //3.创建一个行
        for (int i = 0; i < 65535; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < 10; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(j);
            }
        }
        System.out.println("over");

        //5. 生成一张表
        FileOutputStream fos = new FileOutputStream(PATH + "07版本大数据练习表.xlsx");

        workbook.write(fos);
        //关闭流
        fos.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);//7354
        System.out.println("07文件生成完毕");

    }
```



##### （3） SXSSF

优点：可以写入非常大的数据量，写数据速度快，占用内存更少

**注意：**

* 过程中会产生临时文件，需要**清理临时文件**
* 默认由100条记录被保存在内存中，如果超过这个数量，则

```java
 @Test
    public void test5() throws IOException {

        long start = System.currentTimeMillis();

        //1.创建一个工作簿
        Workbook workbook = new SXSSFWorkbook();

        //2.创建一个工作表
        Sheet sheet = workbook.createSheet("07大数据表2");

        //3.创建一个行
        for (int i = 0; i < 65535; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < 10; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(j);
            }
        }
        System.out.println("over");

        //5. 生成一张表
        FileOutputStream fos = new FileOutputStream(PATH + "07版本大数据练习表2.xlsx");

        workbook.write(fos);
        //关闭流
        fos.close();
        //清除临时文件
        ((SXSSFWorkbook)workbook).dispose();
        long end = System.currentTimeMillis();
        System.out.println(end - start);//1501
        System.out.println("07文件生成完毕");

    }
```



#### 2.5 Excel读取

##### （1） 03版本读取HSSF

```java
    @Test
    public void test1() throws IOException {
        //创建文件流
        FileInputStream fis = new FileInputStream("resources03版本练习表.xls");

        //1. 创建工作簿
        Workbook workbook = new HSSFWorkbook(fis);

        //2. 通过下标得到工作表
        Sheet sheet = workbook.getSheetAt(0);
        //3. 获取数据
        // 获取行
        Row row = sheet.getRow(0);
        // 获取单元格
        Cell cell = row.getCell(0);
        // 获取值 注意类型
        System.out.println(cell.getNumericCellValue());
        //4. 关闭流
        fis.close();
    }

```



##### （2） 07版本读取XSSF

```java
        //创建文件流
        FileInputStream fis = new FileInputStream("resources07版本练习表.xlsx");
        //1. 创建工作簿
        Workbook workbook = new XSSFWorkbook(fis);
        //2. 通过下标得到工作表
        Sheet sheet = workbook.getSheetAt(0);
        //3. 获取数据
        // 获取行
        Row row = sheet.getRow(0);
        // 获取单元格
        Cell cell = row.getCell(0);
        // 获取值 注意类型。
        System.out.println(cell.getNumericCellValue());
        //4. 关闭流
        fis.close();

```



##### （3） 读取不同的类型

```java
//类型判断
@Test
public void test3() throws IOException {
    //创建工作流
    FileInputStream fis = new FileInputStream(PATH + "resources03类型测试.xls");
    //创建工作簿，读取工作流内容
    Workbook workbook = new HSSFWorkbook(fis);
    //获得表
    Sheet sheet = workbook.getSheetAt(0);
    //获取行的数量
    int rows = sheet.getPhysicalNumberOfRows();
    //遍历每一行
    for (int i = 0; i < rows; i++) {
        Row row = sheet.getRow(i);
        if (row != null) {
            //获取列数量
            int numberOfCells = row.getPhysicalNumberOfCells();
            //获取每一个单元格
            for (int j = 0; j <= numberOfCells; j++) {
                Cell cell = row.getCell(j);
                if (cell != null) {
                    int cellType = cell.getCellType();
                    String cellValue = "";
                    //通过switch判断类型，并转换为String类型
                    switch (cellType) {
                        case HSSFCell.CELL_TYPE_STRING://字符串
                            cellValue = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN://布尔类型
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_BLANK://空
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:// 数字
                            //判断是不是日期类型
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {//日期
                                Date date = cell.getDateCellValue();
                                cellValue = new DateTime(date).toString("yyyy-MM-dd");
                            } else {
                                //不是日期格式 ，转换为字符串，防止数字过长
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cellValue = cell.toString();
                            }
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            break;
                    }
                    System.out.print(cellValue + "\t");

                }
            }
            System.out.println();
        }
    }
    //关闭流 
    fis.close();
}
```

注意：可以将switch提取为一个方法，也可以将全部都提取为一个方法，传入一个`FileInputStream`即可



#### 2.6 计算公式（了解即可）

![](data:image/png;base64,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)



```java
@Test
public void test4() throws IOException {
    FileInputStream fis = new FileInputStream(PATH + "计算公式.xls");

    Workbook workbook = new HSSFWorkbook(fis);

    Sheet sheet = workbook.getSheetAt(0);

    Row row = sheet.getRow(3);
    Cell cell = row.getCell(1);

    //拿到计算公式 eval
    FormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);

    //输出单元格的内容
    int cellType = cell.getCellType();
    switch (cellType){
        case Cell.CELL_TYPE_FORMULA://公式
            //获得到公式
            String formula = cell.getCellFormula();
            System.out.println(formula);//  SUM(B2:B3)
            //获取公式计算得到的值
            CellValue evaluate = formulaEvaluator.evaluate(cell);
            String cellValue = evaluate.formatAsString();
            System.out.println(cellValue);//300.0
            break;
    }
    fis.close();
}
```



### 3 easyExcel

* 导入依赖

github：https://github.com/alibaba/easyexcel/blob/master/pom.xml

```xml
<!--easyexcel-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>easyexcel</artifactId>
    <version>2.2.7</version>
</dependency>
```

##### 读和写

参考官方链接：https://github.com/alibaba/easyexcel

* 准备

**对象DemoData**

```java
@Data
public class DemoData {
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
```

**赋值**

```java
private List<DemoData> data() {
    List<DemoData> list = new ArrayList<DemoData>();
    for (int i = 0; i < 10; i++) {
        DemoData data = new DemoData();
        data.setString("字符串" + i);
        data.setDate(new Date());
        data.setDoubleData(0.56);
        list.add(data);
    }
    return list;
}
```



* 写

```java
    final String PATH = "E:\\IDEA项目\\December\\POITest\\src\\main\\";
	//simpleWrite
    @Test
    public void test(){
        String fileName = PATH + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
    }
```



* 读

DemoDAO

```java
public class DemoDAO {
    public void save(List<DemoData> list) {
        // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
    }
}
```

DemoDataListener

```java
public class DemoDataListener extends AnalysisEventListener<DemoData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);

    private static final int BATCH_COUNT = 5;
    List<DemoData> list = new ArrayList<DemoData>();

    private DemoDAO demoDAO;
    public DemoDataListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        demoDAO = new DemoDAO();
    }

    public DemoDataListener(DemoDAO demoDAO) {
        this.demoDAO = demoDAO;
    }
    
    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        System.out.println(JSON.toJSONString(data));
//        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }
   
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }
  
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        demoDAO.save(list);
        LOGGER.info("存储数据库成功！");
    }
}
```

测试

```java
@Test
public void testRead(){
    // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
    // 写法1：
    String fileName = PATH + "EasyExcelTest.xlsx";
    // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
    EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
}
```



### 4 POI 和 easyExcel对比



<img src='data:img/jpg;base64,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
4hMlxsI4p5cAIJ6pIsasqX4AACDQNK25uXl0dNTr9aampHp93ujkMrMgdCMpanj+IUJff//eE00Z
hdXzilNPnarrHxppbu/p7OlbvnKl1+cry8mzMyGoBzKTk82CKJrlYNg/NDpuc3hD4Ygkikl2iygy
xhCI61wbHh+d0lWvzSyH9PEQprickq6SQBwIgBgyFBkAC00p+w4fbGq/8N3vfNdltZOuiyJTlIgs
Stfw+7va498HEADAiRMnzGazz+dzOByAwJAZWQ6i18R2/9cps+HGoQMwRAmE+mN1DrdHMlnqjhzo
HpzyJGfzyb6QxtJyileuqLJbhLpjJ85c6LZZLALRVIAvX1DmtOomX3J7Z6Choe13vr+Zq8onH+4M
KvqajRuTPG6LRufPNH740Ufrnv+trOxku4lQAwpEXv/XVyrWPLlofnk4OLHtrW0ff35k6fpNBSUF
c8qLfE6L/qXUOTT9j+mjdHa2deypzzefHx0dTU1LS072WS1WJn55UzkRIHAiSZKI1NHJ8d7RsYae
QwuqC9o6WptP17t8Gc9U1ZzY92GKrC3ISVYZE2QxKzXjiSceCyoKMly6ZElpWelIiDOrGdAwNdPY
2Nju3bu7urvLy8uzsrN8yd6egTaUGOmocC3kn0rOSP/P//mJufNrJiaG/+b/+/vvfvflovz08Z4L
n544Hw5MAiRjVNv79TbQLYl/JOjs6Pjla6+63DKAwpFsyRkrK4q3//svk9LKntu6frjnzE9e+U2W
74WCouJ39xwLTw6L+lR758DzTz5eVFR06KdvfLDtHZcJh3oGyubMSXLYATkBEQg9PRMvvfRtJRIS
7Y7BKS6YnUK4XxBJkMRAIIwgV1VVhMOD294+pqvsZP3p7KwMp11mMAWAnIy8NzogAAmzoosj4ujo
6N59e33JyQ+vWetwJ21+9LHXX399/969Dz+0Zmp8fNu2d+xWm6qpsmyqqqkuLCk+29z02uuv5+Xl
RSKRpubmzJzscDC05/PPq5cvWDp/YZbL+/Kzz/7s5z///NPPntryJCI2NjedqK/v7u97ZuszHOHs
2cbxkck5c2vzcvN9LqcCkoImFc0mp88riSIGs1y2oQBlpOclyySTPjMt0+V2lVhOTE6cjBwzs6HW
Z4IgCGLjucZ3t73rdrvLSssKCguKCovS09NtNltUJRBz6b++EGUIOucMwWa2hkORd95/3/7suvMd
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
+87b72ze/LHb673+2uumzZh88vDhskKvyyGbIJx+f2VtNQkxa9a0m25Y+9H2vQtnTnG73Fs2fbTl
k43Wo2bOnnv9ddcXFxd/42tf/ttbbz30698qnK5YftUVi2cWy+nbbvvC+hffeHjXJm+e++pr11y5
fNmO7dtSCZfD5ZoxY/pXvvTFfQcPNMyYVuIr1gfuRUEEiiILQa2tJx588MHPfOamL9/15XGf8d3Q
jQMHDiQSibPEyFm+NZlU6vixpk0ff7Br17YvfO3rk6sm7d2+5e03XzjZ1b5gyRWTq8pVhVcUe481
d3z1y19J67HGlo5JNQUOd8GchfMCvsJIMMplqbOj7fW/Prv1cIfklIC0pr1HHT7vOx9/4JVFjHxz
lq39+udvSGYSK69Y7vFXNB1tkxVnbW1VW9uJtrbW6rqq1p54IqMLRJbN+Ah4huACQzeamprLSksn
Tz0Pn4ZTGPHBGgG5AC5LqKoS8pxxEyVXUc01q1b94fE/P/q7rkVzGhSR/uTjdwzZf8stn0Vo5ZzJ
lEHw9N5mnGB5jyACQTwR/+d//ucFCxb+j//xLUVRznXlGWEkQE+m4pEVV62cM3tWJhZ7/bU3fBHt
61/9cp7EOo93b9i6M67j5OLCZYvm796+3VlQMX/+wmK/MxIKMwAAxqW8kpJyj8cNMHxzi2UAME0z
HAr/6Ic/WrN2zXe+813PeI/9M00zFou/8847L7300l133TV//nyHwzHcmyEAZZIZiamf//ztZfme
Iwd3bt366ezFN0yeUi8z7eUnnzD1DCquusmTSwMFBw82Tpu/oGHmDC3caUhuA0GA6fV4yyom9XPi
GWZzIrJ4PLphw4ZPP/30W9/61qpVq4b7UmMYn9fHOU8mU4MetUJrXB7v6muuvWLFKofHiYjInTd+
5nMamU6fWzd0h8N5xZVXzp07N5VOc0nKLy12At58510gO1CRBGf1U6ZVT5kNssOBsGLlyjmLr/B5
nFXVNalU0jRMUwiHw4VMcrqcEucLFyyYUjdZ03TkmOf1OJyyMJQ58xdVVNVouu7Jc3u9XsM0FyxY
yBiTZEmR/NeuuXG+7szPc+u6Bqf54VquarIsFxUV5vvzJ0LsX3d3z4MPPhjID3z37u+e6RwCSqdS
n27a9OIzzx1t71i77jbJ5Y5EIlVlxR2VJeufXr9l79E71n1m6cyyyXUVTz773q3rPpNJtDW1HC0u
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
nvyf/+kNBAoK8hHA5s2nHfXUU0/5/b7i4mLGWDrJ6812u9yhUOjJJ/8nEPCXFJcY1Ni4ceNbb72d
nd2f9NSTT/X19RUWFQqCsGHDhtdff8Pr9Z6RRDBJJ+Xm5DidznA4/Nvf/jYcCpeVlo39OPEMotHo
1q3bXnvtVYvZYrPbT7a2vvfeqra2ttyc3GQyuWvnrjWr15gt5qysrBMnWtasXtPW1padlZ1KpXbv
3rN69RqTyeR2uVtbW1evXt3e1j6Y9N5771mtVofD0dradsZRq1atslgsdru9ra1t9erVQ5POc9TQ
pFWrVnW0d3i93mQyuWvXrtXvrbbb7U6ns/Vk66pVqzo6OrKysjRN2/vR3rVr1zrs9syszOtOH+pS
YeDxZLhdrpWvrNyxY0dhYaHdbm9saHzmmWdEUSgsLEwkkq+88vKOHTtLSkpsVltTc9PTTz9DCCko
yE8kEi+/9PL27TsKCwtUVW1ra3/66WcQguLi4lgs/uKLL+zevbuwsMhkMrWebH3ur89JouT1euPx
+EsvvjSY1HKi5ZlnnyGE5OTkRKPRl158afee/qTjx48/99xzaRObWDw29Kjjx44/99fnZFnOy82L
RKMrVjy/e/fuoqJis9l8/PjxZ555RlVVr9cbjUaff/75nTt3lpSUdnZ0PP/C8x6354bZs8eGDvJ5
CPYG//D7PzQ3N1dUVBKC16/fsH79+w6Hw+PxhMPhF55/obW1Ne3kasP6DWvXrXXYHW63JxIOv/Di
CydbTg4mrV6z2m53ZGR4wuHwX//6187OzoryinTSmrVrHHZHhicjGo2uWLGis7OrfEL5GUmRaHTF
ihUdHR2VFZUI0MaNG1e/t9rlcjkcjlgsPlxSNBpdseL5tta2qsqqdNLatevcbpfL5QqF+las+GtH
R2dNTfVFP5xxN8AfNRAYurFnz57W1lbVpFrM5t5A78EDBw3dsJgtqsl0YP+BkydbLGaLzWrr7Q3U
1dXpmm632yVJqq+vb29rN6mqyWTq7e09cPDgYNK+ffva29utVqvNbvP5/Pvq9g0m7dmzu6uzK23g
6/f599XtwxhbLBZREGprazvaO0xms8Ph6O3t3bevDiNssVgEQdhbW9vR0WGxWNLXOnjwwNlJqsnk
9wfq6/cRQtxutyhKtbW1RxqOnGd5beyAIJXSTpw4Xl9fn0wmnU6HIJDm5ube3l5JlkwmU7Av2NTc
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'/>



