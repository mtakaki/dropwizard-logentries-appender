package com.mtakaki.logentries;

import java.util.TimeZone;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Layout;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.logentries.logback.LogentriesAppender;

import io.dropwizard.logging.AbstractAppenderFactory;
import io.dropwizard.logging.AppenderFactory;

/**
 * An {@link AppenderFactory} implementation which provides an appender that
 * writes events to Logentries service.
 * <p/>
 * <b>Configuration Parameters:</b>
 * <table>
 * <tr>
 * <td>Name</td>
 * <td>Default</td>
 * <td>Description</td>
 * </tr>
 * <tr>
 * <td>{@code threshold}</td>
 * <td>{@code ALL}</td>
 * <td>The lowest level of events to write to the file.</td>
 * </tr>
 * <tr>
 * <td>{@code token}</td>
 * <td><b>REQUIRED</b></td>
 * <td>The account token used by logentries to determine the bucket where the
 * log will be stored.</td>
 * </tr>
 * <tr>
 * <td>{@code timeZone}</td>
 * <td>{@code UTC}</td>
 * <td>The time zone to which event timestamps will be converted.</td>
 * </tr>
 * <tr>
 * <td>{@code useDataHub}</td>
 * <td>{@code false}</td>
 * <td>Whether or not to use logentries data hub. If this is true, it will use
 * the next parameters.</td>
 * </tr>
 * <tr>
 * <td>{@code dataHubAddress}</td>
 * <td><b>REQUIRED</b> if {@code useDataHub} is {@code true}.</td>
 * <td>The data hub address</td>
 * </tr>
 * <tr>
 * <td>{@code dataHubPort}</td>
 * <td>The port for the data hub host.</td>
 * </tr>
 * <tr>
 * <td>{@code location}</td>
 * <td>The location information.</td>
 * <td>
 * The Logback pattern with which events will be formatted. See <a
 * href="http://logback.qos.ch/manual/layouts.html#conversionWord">the Logback
 * documentation</a> for details.</td>
 * </tr>
 * </table>
 *
 * @see AbstractAppenderFactory
 */
@JsonTypeName("logentries")
@Getter
@Setter
public class LogentriesAppenderFactory extends AbstractAppenderFactory {
    private LogentriesAppender appender;

    @NotNull
    private String token;
    @NotNull
    private TimeZone timeZone = TimeZone.getTimeZone("UTC");
    // Datahub specific settings.
    private boolean useDataHub = false;
    private String dataHubAddress;
    private int dataHubPort;
    private String location;
    private boolean logHostName;
    private String hostName;
    private String logId;
    private boolean httpPut;

    @Override
    public Appender<ILoggingEvent> build(final LoggerContext context, final String applicationName,
            final Layout<ILoggingEvent> layout) {
        this.appender = this.buildLogentriesAppender();
        this.appender.setToken(this.token);
        // Unfortunately logentries doesn't support SSL.
        this.appender.setSsl(false);
        this.appender.setContext(context);
        this.appender.setName("logentries");

        if (this.useDataHub) {
            this.appender.setIsUsingDataHub(this.useDataHub);
            this.appender.setDataHubAddr(this.dataHubAddress);
            this.appender.setDataHubPort(this.dataHubPort);
            this.appender.setLocation(this.location);
            this.appender.setLogHostName(this.logHostName);
            this.appender.setHostName(this.hostName);
            this.appender.setLogID(this.logId);
            this.appender.setHttpPut(this.httpPut);
        }

        this.appender.setLayout(layout == null ? this.buildLayout(context, this.timeZone) : layout);
        this.addThresholdFilter(this.appender, this.threshold);
        this.appender.start();

        return this.wrapAsync(this.appender, context);
    }

    protected LogentriesAppender buildLogentriesAppender() {
        return new LogentriesAppender();
    }

//    /**
//     * Creates the LogentriesAppender, setting all the configuration parameters.
//     *
//     * @param context
//     *            The log context.
//     */
//    protected void buildLogentriesAppender(final LoggerContext context) {
//        this.appender = new LogentriesAppender();
//        this.appender.setToken(this.token);
//        // Unfortunately logentries doesn't support SSL.
//        this.appender.setSsl(false);
//        this.appender.setContext(context);
//        this.appender.setName("logentries");
//
//        if (this.useDataHub) {
//            this.appender.setIsUsingDataHub(this.useDataHub);
//            this.appender.setDataHubAddr(this.dataHubAddress);
//            this.appender.setDataHubPort(this.dataHubPort);
//            this.appender.setLocation(this.location);
//            this.appender.setLogHostName(this.logHostName);
//            this.appender.setHostName(this.hostName);
//            this.appender.setLogID(this.logId);
//            this.appender.setHttpPut(this.httpPut);
//        }
//    }
}