package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/apis")
public class FetchingData {
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/JSON")
	private static void getEmployees(HttpServletResponse response) {
		try {
			System.out.printlN("jenkin testing");
			// final String uri =
			// "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=4043f61118a64f559416fe4966557758";
			URL url = new URL(
					"https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=4043f61118a64f559416fe4966557758");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			/*
			 * BufferedReader br = new BufferedReader(new InputStreamReader(
			 * (conn.getInputStream())));
			 * 
			 * String output; System.out.println("Output from Server .... \n"); while
			 * ((output = br.readLine()) != null) { System.out.println(output); }
			 */
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			System.out.println(br);

			String output;
			List<String> abc = new ArrayList<String>();
			while ((output = br.readLine()) != null) {
				abc.add(output);
				System.out.println("inside");
			}
			System.out.print(abc);
			abc.forEach(k->{
				System.out.println(k);
			});
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			out.print(abc);

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
