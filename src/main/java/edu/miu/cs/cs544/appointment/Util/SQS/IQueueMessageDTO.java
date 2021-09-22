package edu.miu.cs.cs544.appointment.Util.SQS;


public interface IQueueMessageDTO<T> {
    public String getQueueName();
    public T getMessage();
}
