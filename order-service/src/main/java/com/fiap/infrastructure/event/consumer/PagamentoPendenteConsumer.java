package com.fiap.infrastructure.event.consumer;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import contracts.events.PagamentoPendenteEvent;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import org.eclipse.microprofile.reactive.messaging.Incoming;
//
//@ApplicationScoped
//public class PagamentoPendenteConsumer {
//
//    @Inject
//    ObjectMapper mapper;
//
//    @Incoming("pagamento-pendente-in")
//    public void consume(String json) {
//        try {
//            var event = mapper.readValue(json, PagamentoPendenteEvent.class);
//            // Log para confirmar que funcionou
//            System.out.println("Evento Pendente Recebido: " + event.pedidoId());
//        } catch (Exception e) {
//            System.err.println("Erro ao ler JSON: " + e.getMessage());
//        }
//    }
//}
