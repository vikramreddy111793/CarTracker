package com.vbhoomidi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vbhoomidi.AppConfig;
import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.service.VehicleListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by vikramreddy on 7/12/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class VehicleListControllerTest {

    @Mock
    private VehicleListService vehicleListService;

    @InjectMocks
    private VehicleListController vehicleListController;

    private MockMvc mockMvc;

    @Before
    public void initializeMockito(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleListController).build();
    }

    @Test
    public void ListAllVehiclesTest() throws Exception{
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

        when(vehicleListService.findAll()).thenReturn(list);

        mockMvc.perform(get("/vehicles"))
               .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].vin", is("1HGCR2F3XFA027534")))
                .andExpect(jsonPath("$[1].vin", is("WP1AB29P63LA60179")));
    }

    @Test
    public void FindOneVehicle_Success_Test() throws Exception{
        VehicleInfo vehicle = new VehicleInfo();
        vehicle.setVin("WP1AB29P63LA60179");
        vehicle.setMake("PORSCHE");
        vehicle.setModel("CAYENNE");
        vehicle.setYear(2015);
        vehicle.setRedlineRpm(8000);
        vehicle.setMaxFuelVolume(18);
        vehicle.setLastServiceDate(null);

        when(vehicleListService.findOne(vehicle.getId())).thenReturn(vehicle);

        mockMvc.perform(get("/vehicles/"+vehicle.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.vin", is("WP1AB29P63LA60179")));
    }

    @Test
    public void FindOneVehicle_Failure_Test() throws Exception{
        VehicleInfo vehicle = new VehicleInfo();
        vehicle.setVin("WP1AB29P63LA60179");
        vehicle.setMake("PORSCHE");
        vehicle.setModel("CAYENNE");
        vehicle.setYear(2015);
        vehicle.setRedlineRpm(8000);
        vehicle.setMaxFuelVolume(18);
        vehicle.setLastServiceDate(null);

        when(vehicleListService.findOne(vehicle.getId())).thenReturn(vehicle);

        mockMvc.perform(get("vehicles/lejsfygrjh5243df36v84df"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void AddVehiclesTest() throws Exception{
        VehicleInfo[] list = new VehicleInfo[2];
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
        list[0]=v1;
        list[1]=v2;
        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(list);

        vehicleListService.create(list);

        mockMvc.perform(put("/vehicles")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().isOk());
    }
}
