package com.example.MeeshoApp.common;

import static com.example.MeeshoApp.common.Base_url.URL_FORGET_PASSWORD;
import static com.example.MeeshoApp.common.Base_url.URL_GENERATE_PASSWORD;
import static com.example.MeeshoApp.common.Base_url.URL_GET_CITY;
import static com.example.MeeshoApp.common.Base_url.URL_GET_DISTRICT;
import static com.example.MeeshoApp.common.Base_url.URL_GET_STATE;
import static com.example.MeeshoApp.common.Base_url.URL_LOGIN;
import static com.example.MeeshoApp.common.Base_url.URL_OTP_VERIFICATION;
import static com.example.MeeshoApp.common.Base_url.URL_USER_REGISTRATION;

import com.example.MeeshoApp.Model.MyorderModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api{


    @POST(URL_GET_STATE)
    Call<ResponseBody> getState();

    @FormUrlEncoded
    @POST(URL_GET_DISTRICT)
    Call<ResponseBody> getDistrict(@Field("state_id") String stateId);

    @FormUrlEncoded
    @POST(URL_GET_CITY)
    Call<ResponseBody> getCity(@Field("district_id") String districtId);

    @FormUrlEncoded
    @POST(URL_USER_REGISTRATION)
    Call<ResponseBody> registration(@Field("first_name") String firstName,
                                    @Field("last_name") String lastName,
                                    @Field("mobile") String mobile,
                                    @Field("email") String email,
                                    @Field("state_id") String stateId,
                                    @Field("district_id") String districtId,
                                    @Field("city_id") String cityId,
                                    @Field("pincode") String pincode,
                                    @Field("address") String address,
                                    @Field("refer_code") String referCode,
                                    @Field("password") String password,
                                    @Field("confirm_password") String confirmPassword);

    @FormUrlEncoded
    @POST(URL_LOGIN)
    Call<ResponseBody> login(@Field("mobile") String mobile,
                             @Field("password") String password);
    @FormUrlEncoded
    @POST(URL_LOGIN)
    Call<ResponseBody> login_token(@Field("mobile") String mobile,
                                   @Field("password") String password);
    @FormUrlEncoded
    @POST(URL_OTP_VERIFICATION)
    Call<ResponseBody> otpVerification(@Field("mobile") String mobile,
                                       @Field("otp") String otp,
                                       @Field("resend") String resend,
                                       @Field("type") String type);//type=0:login && 1:forget password
    @FormUrlEncoded
    @POST(URL_FORGET_PASSWORD)
    Call<ResponseBody> forgotPassword(@Field("mobile") String mobile);
    @FormUrlEncoded
    @POST(URL_GENERATE_PASSWORD)
    Call<ResponseBody> generatePassword(@Field("mobile") String mobile,
                                        @Field("password") String password);


}
