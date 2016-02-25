package com.github.mtakaki.logentries;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;

import com.github.mtakaki.logentries.LogentriesAppenderFactory;
import com.logentries.logback.LogentriesAppender;
import com.logentries.net.AsyncLogger;

@RunWith(MockitoJUnitRunner.class)
public class LogentriesAppenderFactoryTest {
    private static final String APPLICATION_NAME = "my_app";

    private LogentriesAppenderFactory appenderFactory;

    @Mock
    private LoggerContext context;

    @Mock
    private Layout<ILoggingEvent> layout;

    @Mock
    private AsyncLogger asyncLogger;

    @Before
    public void setup() {
        this.appenderFactory = new LogentriesAppenderFactory();
    }

    @Test
    public void testBuild() {
        final AsyncAppender appender = (AsyncAppender) this.appenderFactory.build(this.context,
                APPLICATION_NAME, this.layout);

        assertThat(appender.getName(), equalTo("async-logentries"));
        assertThat(appender.getAppender("logentries"), instanceOf(LogentriesAppender.class));
    }
}