package com.microservice.rating.messages.listeners;

public interface CanReceiveMessage {
	void receiveMessage(Object message);
}
