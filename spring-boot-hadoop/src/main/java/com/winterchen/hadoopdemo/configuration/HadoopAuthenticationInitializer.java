package com.winterchen.hadoopdemo.configuration;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.streaming.Environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.security.PrivilegedAction;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author winterchen
 * @version 1.0
 * @date 2020/11/19 7:19 下午
 * @description TODO
 **/
public class HadoopAuthenticationInitializer {

    private UserGroupInformation userGroupInformation;
    private Timer timer;
    private long timeoutInterval = 600000L;
    private String principle;
    private String keytab;
    private boolean enabled;
    private boolean forceReauth = false;
    private long nextReauthenticationAttempt = 0L;

    public HadoopAuthenticationInitializer() {
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Configuration conf = new Configuration();
        conf.set("hadoop.security.authentication", "kerberos");
        UserGroupInformation.setConfiguration(conf);

        try {
            Environment ex = (Environment)beanFactory.getBean(Environment.class);
            String enabledStr = ex.getProperty("hadoop.enabled", "true");
            this.enabled = "true".equals(enabledStr);

            if(this.enabled) {
                this.timer = new Timer();
                this.principle = ex.getProperty("hadoop.principle");
                this.keytab = ex.getProperty("hadoop.keytab");
                this.timeoutInterval = Long.valueOf(ex.getProperty("hadoop.keyRenewIntervalMs", String.valueOf(600000L))).longValue();
                UserGroupInformation.loginUserFromKeytab(this.principle, this.keytab);
                this.userGroupInformation = UserGroupInformation.getLoginUser();
                this.timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            HadoopAuthenticationInitializer.this.forceReauth = true;
                            HadoopAuthenticationInitializer.this.reauth();
                        } catch (Exception var) {

                        }
                    }
                }, this.timeoutInterval, this.timeoutInterval);

            } else {
                System.out.print("");
            }

        } catch (Exception ex) {

        }
    }

    private void run() {

    }


    private synchronized void reauth() {
        if(this.enabled) {
            try {
                if(!this.forceReauth && this.userGroupInformation != null) {
                    this.userGroupInformation.checkTGTAndReloginFromKeytab();
                } else {
                    this.forceReauth = false;
                    long ex = System.currentTimeMillis();
                    if(this.nextReauthenticationAttempt < ex) {
                        this.nextReauthenticationAttempt = ex + (this.timeoutInterval - 1000L);
                        UserGroupInformation.loginUserFromKeytab(this.principle, this.keytab);
                        this.userGroupInformation = UserGroupInformation.getLoginUser();
                    }
                }
            } catch (Exception var3) {
                System.out.println("");
            }
        }
    }

    public void destroy() throws Exception {
        if(this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
    }

    public void checkAuth() {
        this.reauth();
    }

    public <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
        this.checkAuth();
        if(this.enabled && this.userGroupInformation != null) {
            return this.userGroupInformation.doAs(privilegedAction);
        } else {
            throw new IllegalStateException("");
        }
    }
}
