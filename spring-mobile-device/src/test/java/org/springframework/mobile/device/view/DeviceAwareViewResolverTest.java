package org.springframework.mobile.device.view;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceType;
import org.springframework.mobile.device.StubDevice;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.mobile.device.view.DeviceAwareViewResolver;

public final class DeviceAwareViewResolverTest {

	private final DeviceAwareViewResolver viewResolver = new DeviceAwareViewResolver();

	private final String viewName = "home";

	@Before
	public void setUp() {
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setNormalPrefix("/normal/");
		viewResolver.setMobilePrefix("/mobile/");
	}

	@Test
	public void testMobileViewResolution() throws Exception {
		final Device device = new StubDevice(DeviceType.MOBILE);
		final String resolvedViewName = viewResolver.resolveDeviceAwareViewName(viewName, device, null);
		Assert.assertEquals("mobile/home", resolvedViewName);
	}

	@Test
	public void testMobileViewResolutionWithMobileSitePreference() throws Exception {
		final Device device = new StubDevice(DeviceType.MOBILE);
		final SitePreference sitePreference = SitePreference.MOBILE;
		final String resolvedViewName = viewResolver.resolveDeviceAwareViewName(viewName, device, sitePreference);
		Assert.assertEquals("mobile/home", resolvedViewName);
	}

	@Test
	public void testMobileViewResolutionWithNormalSitePreference() throws Exception {
		final Device device = new StubDevice(DeviceType.MOBILE);
		final SitePreference sitePreference = SitePreference.NORMAL;
		final String resolvedViewName = viewResolver.resolveDeviceAwareViewName(viewName, device, sitePreference);
		Assert.assertEquals("normal/home", resolvedViewName);
	}

	@Test
	public void testNormalViewResolution() throws Exception {
		final Device device = new StubDevice(DeviceType.NORMAL);
		final String resolvedViewName = viewResolver.resolveDeviceAwareViewName(viewName, device, null);
		Assert.assertEquals("normal/home", resolvedViewName);
	}

}
