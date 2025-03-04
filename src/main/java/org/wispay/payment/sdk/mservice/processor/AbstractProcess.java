package org.wispay.payment.sdk.mservice.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.wispay.payment.sdk.mservice.common.utils.LogUtils;
import org.wispay.payment.sdk.mservice.common.utils.WisPayExecutor;
import org.wispay.payment.sdk.mservice.config.Environment;
import org.wispay.payment.sdk.mservice.config.PartnerInfo;

public abstract class AbstractProcess<T, V> {
    protected PartnerInfo partnerInfo;
    protected Environment environment;
    protected WisPayExecutor executor = new WisPayExecutor();

    public AbstractProcess(Environment environment) {
        this.environment = environment;
        this.partnerInfo = environment.getPartnerInfo();
    }

    public static Gson getGson() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .create();
    }

    public static void log(String message, Object data) {
        LogUtils.log(message, data);
    }

    public abstract V execute(T request);
}
