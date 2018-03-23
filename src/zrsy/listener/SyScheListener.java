package zrsy.listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import zrsy.facade.SyScheFacade;
import zrsy.vo.SySche;

public class SyScheListener  implements ServletContextListener {

	private java.util.Timer timer = null; 
	public void contextDestroyed(ServletContextEvent cte) {
		timer.cancel();
	}

	public void contextInitialized(ServletContextEvent cte) {
		timer = new Timer(true);
		try {
			List<SySche>  sySches = new SyScheFacade().find("select " + SySche.ALL_FIELDS +
					" from SySche ", SySche.ALL_FIELDS);
			
			for(int i=0; i<sySches.size(); i++) {
				if(sySches.get(i).getScheUnit().equals(2)) { //天
					SimpleDateFormat formatter1 = new  SimpleDateFormat ( "yyyy-MM-dd ");
					String now = formatter1.format(sySches.get(i).getStartTime());
					
					SimpleDateFormat formatter2 = new  SimpleDateFormat ( "HH:mm:ss" );
					String time = formatter2.format(sySches.get(i).getStartTime());
					
					SimpleDateFormat formatter3 = new  SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
					
					timer4Day(sySches.get(i).getScheUri(),formatter3.parse(now+time),sySches.get(i).getScheDay());
				}
				else if(sySches.get(i).getScheUnit().equals(1)) { //小时
					SimpleDateFormat formatter1 = new  SimpleDateFormat ( "yyyy-MM-dd ");
					String now = formatter1.format(sySches.get(i).getStartTime());
					
					SimpleDateFormat formatter2 = new  SimpleDateFormat ( "HH:mm:ss" );
					String time = formatter2.format(sySches.get(i).getStartTime());
					
					SimpleDateFormat formatter3 = new  SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
					
					timer4Day(sySches.get(i).getScheUri(),formatter3.parse(now+time),sySches.get(i).getScheDay());
				}
				else if(sySches.get(i).getScheUnit().equals(0)) { //分钟
					timer4Minute(sySches.get(i).getScheUri(),sySches.get(i).getScheDay());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *  按天-周期数执行
	 * @param clazz 实现类
	 * @param date 开始时间
	 * @param c  周期（1，2，3...天）
	 */
	public void timer4Day(String clazz,Date date,int c) {
		try {
			timer.schedule((TimerTask)Class.forName(clazz).newInstance(), date, 24*60*60*1000*c);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *  按小时-周期数执行
	 * @param clazz 实现类
	 * @param date 开始时间
	 * @param c  周期（1，2，3...小时）
	 */
	public void timer4Hour(String clazz,Date date,int c) {
		try {
			timer.schedule((TimerTask)Class.forName(clazz).newInstance(), date, 60*60*1000*c);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *  按分钟-周期数执行
	 * @param clazz 实现类
	 * @param date 开始时间
	 * @param c  周期（1，2，3...分钟）
	 */
	public void timer4Minute(String clazz,int m2) { //m1:60000 * x分钟,
		try {
			timer.schedule((TimerTask)Class.forName(clazz).newInstance(), 60000,60000 * m2);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
