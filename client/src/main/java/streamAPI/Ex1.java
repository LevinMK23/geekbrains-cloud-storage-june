package streamAPI;

import javax.swing.*;

public class Ex1 {

    public static void main(String[] args) {
        Runnable run = new Runnable() {
            @Override
            public void run() {

            }
        };
        Runnable lRun = () -> {

        };
        new JButton().addActionListener(a -> {

        });
        System.out.println(run.getClass());
        System.out.println(lRun.getClass());
    }
}
