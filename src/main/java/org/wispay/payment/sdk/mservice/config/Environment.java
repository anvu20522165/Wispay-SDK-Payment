package org.wispay.payment.sdk.mservice.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment {
    private static final Log log = LogFactory.getLog(Environment.class);
    private PartnerInfo partnerInfo;
    private WisPayEndpoint wisPayEndpoint;
    private String target;


    public Environment(PartnerInfo partnerInfo, WisPayEndpoint wisPayEndpoint, String target) {
        this.partnerInfo = partnerInfo;
        this.wisPayEndpoint = wisPayEndpoint;
        this.target = target;
    }

    public Environment(PartnerInfo partnerInfo, WisPayEndpoint wisPayEndpoint, EnvTarget target) {
        this(partnerInfo, wisPayEndpoint, target.string());
    }

    public static Environment selectEnv(String target) throws IllegalArgumentException {
        switch (target) {
            case "local":
                return selectEnv(EnvTarget.LOCAL);
            case "dev":
                return selectEnv(EnvTarget.DEV);
            case "prod":
                return selectEnv(EnvTarget.PROD);
            default:
                throw new IllegalArgumentException("Wispay has not implemented other environment, except: dev and prod");
        }
    }

    /**
     * Select appropriate environment to run processes
     * Create and modify your environment.properties file appropriately
     *
     * @param target EnvTarget (choose DEV or PROD)
     * @return
     */
    public static Environment selectEnv(EnvTarget target) {
        try (InputStream input = Environment.class.getClassLoader().getResourceAsStream("environment.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            switch (target) {
                case LOCAL:
                    WisPayEndpoint localEndpoint = new WisPayEndpoint(prop.getProperty("LOCAL_WISPAY_PAYMENT_ENDPOINT"));
                    PartnerInfo localInfo = new PartnerInfo(prop.getProperty("LOCAL_API_KEY"), prop.getProperty("LOCAL_SECRET_KEY"));
                    Environment local = new Environment(localInfo, localEndpoint, target);
                    return local;
                case DEV:
                    WisPayEndpoint devEndpoint = new WisPayEndpoint(prop.getProperty("DEV_WISPAY_PAYMENT_ENDPOINT"));
                    PartnerInfo devInfo = new PartnerInfo(prop.getProperty("DEV_API_KEY"), prop.getProperty("DEV_SECRET_KEY"));
                    Environment dev = new Environment(devInfo, devEndpoint, target);
                    return dev;

                default:
                    throw new IllegalArgumentException("Wispay has not implemented other environment, except: dev and prod");
            }

        } catch (IOException e) {
            log.error(e);
        }
        return null;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public PartnerInfo getPartnerInfo() {
        return partnerInfo;
    }

    public void setPartnerInfo(PartnerInfo partnerInfo) {
        this.partnerInfo = partnerInfo;
    }

    public WisPayEndpoint getWisPayEndpoint() {
        return wisPayEndpoint;
    }

    public void setWisPayEndpoint(WisPayEndpoint wisPayEndpoint) {
        this.wisPayEndpoint = wisPayEndpoint;
    }

    public enum EnvTarget {
        LOCAL("local"), DEV("development"), PROD("production");

        private String target;

        EnvTarget(String target) {
            this.target = target;
        }

        public String string() {
            return this.target;
        }
    }


    public enum ProcessType {
        PAY_GATE, APP_IN_APP, PAY_POS, PAY_QUERY_STATUS, PAY_REFUND, PAY_CONFIRM;

        public String getSubDir(Properties prop) {
            return prop.getProperty(this.toString());
        }
    }

}
