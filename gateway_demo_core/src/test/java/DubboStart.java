import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 
*
* @Description: Dubbo服务启动
* @ClassName: DubboStart 
* @author zhufj
* @date 2019年3月4日 下午5:53:20 
*
 */
public class DubboStart {
  
	public static void main(String[] args) throws Exception{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
		context.start(); 
		System.out.println("Please print any key ....");    
		System.in.read();    
	}
}
