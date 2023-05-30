//import { connect, Channel, Connection } from 'amqplib';

import type {Channel, Connection} from "amqplib";
import {connect} from "amqplib";

export  default async function connectToRabbitMQ(): Promise<void> {
    try {
        const connection: Connection = await connect('amqp://localhost:5672');
        const channel: Channel = await connection.createChannel();

        // Perform further operations (publishing and consuming messages) with the channel

        console.log('Connected to RabbitMQ');
    } catch (error) {
        console.error('Failed to connect to RabbitMQ', error);
    }
}

