package com.revature.services;

import com.uni.daos.GameRequestDAO;
import com.uni.datautils.ConnectionUtil;
import com.uni.entities.Game;
import com.uni.entities.GameRequest;
import com.uni.services.GameRequestImpl;
import com.uni.services.GameRequestService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GameRequestServiceIntegrationTest {
    private GameRequestImpl gameRequestService;
    private GameRequestDAO gameRequestDAO;

    @BeforeEach
    public void injectDAO(){
        gameRequestDAO = GameRequestDAO.getSingleton();
        gameRequestService = new GameRequestImpl(gameRequestDAO);
    }

    @BeforeEach
    public void populateDatabase() throws SQLException {
        try(Connection conn = ConnectionUtil.getConnection()){
            ConnectionUtil.populateH2Database(conn);
        }
    }

    @AfterEach
    public void clearDatabase() throws SQLException{
        try(Connection conn = ConnectionUtil.getConnection()){
            ConnectionUtil.clearH2Database(conn);
        }
    }

    @DisplayName("Create request")
    @Test
    public void createRequest(){
        GameRequest test = new GameRequest(1, 1, 1, "Main Campus Gym: Court 1", "season 1");
        GameRequest actual = gameRequestService.createRequest(test);
        assertEquals(test,actual);
    }

    // work on this
    @DisplayName("Delete request")
    @Test
    public void deleteRequest(){
        gameRequestDAO.delete(3,1);
        //
        //Assert.assertNull(gameRequestService.getAllGamesAndReferees());
        assertEquals(1, gameRequestService.getAllGamesAndReferees().size());
    }

    @DisplayName("Get all games and referees")
    @Test
    public void getAllGamesAndReferees(){
        List<GameRequest> actual = gameRequestService.getAllGamesAndReferees();
        /*Game test = new Game(1, "Main Campus Gym: Court 1", "season 1", "Grand Dunk Railroad", "The Ballers",
                             0, 0, 0, "scheduled");*/

        assertEquals(1, actual.size());
    }


}
