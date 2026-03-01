//package com.fiap.infrastructure.event.listener;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fiap.core.event.PedidoCriadoEvent;
//import io.smallrye.reactive.messaging.annotations.Blocking;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import org.eclipse.microprofile.reactive.messaging.Incoming;
//import org.jboss.logging.Logger;
//
//@ApplicationScoped
//public class PedidoCriadoListener {
//
//    private static final Logger LOG =
//            Logger.getLogger(PedidoCriadoListener.class);
//
//    @Inject
//    ObjectMapper objectMapper;
//
//    @Incoming("pedido-criado-in")
//    @Blocking
//    public void on(String payload) {
//        try {
//            PedidoCriadoEvent event =
//                    objectMapper.readValue(payload, PedidoCriadoEvent.class);
//
//            LOG.infof(
//                    "📦 Pedido criado recebido | pedidoId=%s | clienteId=%s",
//                    event.pedidoId(),
//                    event.clienteId()
//            );
//
//        } catch (Exception e) {
//            // nunca derrube o consumer Kafka
//            LOG.error("Erro ao processar pedido.criado", e);
//        }
//    }
//}
