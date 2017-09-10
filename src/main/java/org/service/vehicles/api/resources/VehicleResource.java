package org.service.vehicles.api.resources;

import com.google.gson.JsonSyntaxException;
import org.service.vehicles.api.core.GsonHelper;
import org.service.vehicles.api.services.VehicleService;
import org.service.vehicles.api.services.dataobject.ApiError;
import org.service.vehicles.api.services.dataobject.Vehicle;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by ramya on 17/2/17.
 */
@Path("/vehicle")
public class VehicleResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response createVehicle(String vehicleJson) {
        VehicleService vehicleService = new VehicleService();
        ApiError apiError = new ApiError();
        try {
            Vehicle vehicle = GsonHelper.fromJson(vehicleJson, Vehicle.class);
            apiError = vehicleService.validate(vehicle);
            if (apiError == null) {
                int vehicleId = vehicleService.createorUpdateVehicle(vehicle);
                if (vehicleId != -1) {
                    return Response.status(Response.Status.CREATED).entity(vehicle).build();
                } else {
                    apiError.setErrorCode(9000);
                    apiError.setErrorMessage("Unable to create the vehicle. Please try again later");
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
                }
            } else {
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(apiError).build();
            }
        } catch (JsonSyntaxException jEx) {
            apiError.setErrorCode(9001);
            apiError.setErrorMessage("Invalid JSON");
            return Response.status(Response.Status.BAD_REQUEST).entity(apiError).build();
        }
    }


    @GET
    @Path("/{vId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicleByID(@PathParam("vId") String vehicleIdParam, @QueryParam("make") String sMake) {
        System.out.println("sMake = " + sMake);
        System.out.println("vehicleIdParam = " + vehicleIdParam);

        VehicleService vehicleService = new VehicleService();
        ApiError apiError = new ApiError();
        try {
            int vehicleId = Integer.parseInt(vehicleIdParam);
            Vehicle vehicleById = vehicleService.getVehicleById(vehicleId);
            if (vehicleById != null) {
                return Response.status(Response.Status.CREATED).entity(vehicleById).build();
            } else {
                apiError.setErrorCode(9000);
                apiError.setErrorMessage("Unable to get the vehicle. Vehicle with Id [" + vehicleIdParam + "] does not exists.");
                return Response.status(Response.Status.NOT_FOUND).entity(apiError).build();
            }
        } catch (JsonSyntaxException jEx) {
            apiError.setErrorCode(9001);
            apiError.setErrorMessage("Invalid JSON");
            return Response.status(Response.Status.BAD_REQUEST).entity(apiError).build();
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicle(@QueryParam("make") String sMake, @QueryParam("model") String sModel) {
        VehicleService vehicleService = new VehicleService();
        ApiError apiError = new ApiError();
        try {
            ArrayList<Vehicle> matchedVehicles = null;

            if (sMake == null && sModel == null) { // Return all Vehicles
                matchedVehicles = vehicleService.getVehicles();
            }

            if (sMake != null) { // Return Vehicles Matching Make
                matchedVehicles = vehicleService.getVehicleByMake(sMake);
            }else
            if (sModel != null) { // Return Vehicles Matching Model
                matchedVehicles = vehicleService.getVehicleByModel(sModel);
            }else
            if (sMake!= null && sModel!=null){
                matchedVehicles = null;
                matchedVehicles = vehicleService.getVehicleByModelAndMake(sModel,sMake);
            }

            if (matchedVehicles != null && matchedVehicles.size() > 0) {
                return Response.status(Response.Status.OK).entity(matchedVehicles).build();
            } else {
                apiError.setErrorCode(9000);
                apiError.setErrorMessage("Unable to get the vehicle. Vehicle with Make [" + sMake + "] does not exists.");
                return Response.status(Response.Status.NOT_FOUND).entity(apiError).build();
            }
        } catch (JsonSyntaxException jEx) {
            apiError.setErrorCode(9001);
            apiError.setErrorMessage("Invalid JSON");
            return Response.status(Response.Status.BAD_REQUEST).entity(apiError).build();
        }
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{vId}")
    public Response updateVehicle(String vehicleJson, @PathParam("vId") String vehicleIdParam) {
        VehicleService vehicleService = new VehicleService();

        System.out.println("inside");
        ApiError apiError = new ApiError();
        try {
            Vehicle vehicle = GsonHelper.fromJson(vehicleJson, Vehicle.class);
            //TODO: Should not change the ID. add Logic For it
            Vehicle existingVehicle = vehicleService.getVehicleById(vehicle.getId());
            int vehicleId = -1;
            if(existingVehicle!= null && existingVehicle.getId() == vehicle.getId())
                vehicleId = vehicleService.createorUpdateVehicle(vehicle);
            if (vehicleId != -1) {
                return Response.status(Response.Status.CREATED).entity(vehicle).build();
            } else {
                apiError.setErrorCode(9000);
                apiError.setErrorMessage("Unable to update the vehicle. Vehicle with Id [" + vehicleIdParam + "] does not exists.");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
            }
        } catch (JsonSyntaxException jEx) {
            apiError.setErrorCode(9001);
            apiError.setErrorMessage("Invalid JSON");
            return Response.status(Response.Status.BAD_REQUEST).entity(apiError).build();
        }
    }

    @DELETE
    @Path("/{vId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteVehicle(@PathParam("vId") String vehicleIdParam) {

        VehicleService vehicleService = new VehicleService();
        ApiError apiError = new ApiError();
        try {
            Boolean success = false;
            int vehicleId = Integer.parseInt(vehicleIdParam);
            Vehicle vehicleToBeDeleted = vehicleService.getVehicleById(vehicleId);
            if(vehicleToBeDeleted != null) {
                vehicleService.getDelete(vehicleService.getVehicleById(vehicleId));
                success = true;
            }


            if (success) {
                return Response.status(Response.Status.OK).build();
            } else {
                apiError.setErrorCode(9000);
                apiError.setErrorMessage("Unable to delete the vehicle. Vehicle with Id [" + vehicleIdParam + "] does not exists.");
                return Response.status(Response.Status.NOT_FOUND).entity(apiError).build();
            }
        } catch (JsonSyntaxException jEx) {
            apiError.setErrorCode(9001);
            apiError.setErrorMessage("Invalid JSON");
            return Response.status(Response.Status.BAD_REQUEST).entity(apiError).build();
        }
    }


}
