package com.vbhoomidi.service;

import com.vbhoomidi.AppConfig;
import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.exception.ResourceNotFoundException;
import com.vbhoomidi.repository.VehicleListRepository;
import com.vbhoomidi.service.impl.VehicleListServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by vikramreddy on 7/12/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class VehicleListServiceTest {

    @Mock
    private VehicleListRepository vehicleListRepository;

    @InjectMocks
    private VehicleListServiceImpl vehicleListService = new VehicleListServiceImpl();

    @Before
    public void initializeMockito(){
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void findAll_Success_Test() throws Exception{
        List<VehicleInfo> list = new ArrayList<>();
        VehicleInfo v1 = new VehicleInfo();
        VehicleInfo v2 = new VehicleInfo();
        v1.setVin("1HGCR2F3XFA027534");
        v1.setMake("HONDA");
        v1.setModel("ACCORD");
        v1.setYear(2015);
        v1.setRedlineRpm(5500);
        v1.setLastServiceDate(null);
        v1.setMaxFuelVolume(15);
        v2.setVin("WP1AB29P63LA60179");
        v2.setMake("PORSCHE");
        v2.setModel("CAYENNE");
        v2.setYear(2015);
        v2.setRedlineRpm(8000);
        v2.setMaxFuelVolume(18);
        v2.setLastServiceDate(null);
        list.add(v1);
        list.add(v2);

        when(vehicleListRepository.findAll()).thenReturn(list);
        List<VehicleInfo> response = vehicleListService.findAll().orElse(null);
        assertNotNull(response);
        assertEquals(list.get(0).getId(), response.get(0).getId());
        assertEquals(list.get(1).getVin(), response.get(1).getVin());
    }

    @Test
    public void findAll_Fail_Test() throws Exception{
        when(vehicleListRepository.findAll()).thenReturn(null);
        List<VehicleInfo> response = vehicleListService.findAll().orElse(null);
        assertEquals(null,null);
    }

    @Test
    public void findOne_Success_Test() throws Exception{
        VehicleInfo vehicle = new VehicleInfo();
        vehicle.setVin("WP1AB29P63LA60179");
        vehicle.setMake("PORSCHE");
        vehicle.setModel("CAYENNE");
        vehicle.setYear(2015);
        vehicle.setRedlineRpm(8000);
        vehicle.setMaxFuelVolume(18);
        vehicle.setLastServiceDate(null);

        when(vehicleListRepository.findOne(vehicle.getId())).thenReturn(Optional.ofNullable(vehicle));

        VehicleInfo response = vehicleListService.findOne(vehicle.getId());
        assertNotNull(response);
        assertEquals(vehicle.getVin(), response.getVin());
        assertEquals(vehicle.getMake(), response.getMake());
    }

    @Test
    public void findOne_Fail_Test() throws Exception{
        String id = anyString();
        when(vehicleListRepository.findOne(id)).thenReturn(Optional.ofNullable(null));

        expectedException.expect(ResourceNotFoundException.class);
        expectedException.expectMessage("Vehicle with id-"+id+" doesn't exist");
        vehicleListService.findOne(id);
    }

}
