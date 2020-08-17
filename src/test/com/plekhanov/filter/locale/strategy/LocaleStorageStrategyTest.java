package com.plekhanov.filter.locale.strategy;

import com.plekhanov.filter.locale.requestwrapper.LocaleRequestWrapper;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletResponse;

public class LocaleStorageStrategyTest {
    @Mock
    protected HttpServletResponse response;
    @Mock
    protected LocaleRequestWrapper localeRequestWrapper;
    protected LocaleStorageStrategy localeStorageStrategy;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}