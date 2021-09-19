package edu.miu.cs.cs544.appointment.Payload.Requests;

import java.util.List;

public interface SearchResult<T> {
	List<T> getResult();

	int getCount();
}
