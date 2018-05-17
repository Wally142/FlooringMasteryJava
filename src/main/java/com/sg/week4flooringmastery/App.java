
package com.sg.week4flooringmastery;

import com.sg.week4flooringmastery.controller.FlooringMasteryController;
import com.sg.week4flooringmastery.ui.FlooringMasteryView;
import com.sg.week4flooringmastery.ui.UserIO;
import com.sg.week4flooringmastery.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {
    public static void main(String[] args) {
        
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller
                = ctx.getBean("controller", FlooringMasteryController.class);
        controller.run();
        
    }

}
