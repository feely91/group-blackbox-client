package com.kainos.training.blackbox.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.kainos.training.blackboxinterface.model.person.Person;

public class FriendClient {

	// "http://localhost:8910/person"
	private Client client;
	private String serverRoot;
	private WebTarget webTarget;

	public FriendClient(String serverAddress) {
		client = ClientBuilder.newClient();
		serverRoot = serverAddress;

		webTarget = client.target(serverRoot);
	}

	public Response addFriend(Person person) {
		Response r = webTarget.request().post(
				Entity.entity(person, MediaType.APPLICATION_JSON));
		return r;
	}

	public Response removeFriend(Person person) {

		WebTarget deleteTarget = webTarget.path("/" + person.getId());
		Response r = deleteTarget.request().delete();
		return r;
	}

	public List<Person> getFriendsList() {
		Response response = webTarget.request().get();
		return response.readEntity(new GenericType<List<Person>>() {
		});
	}
}
