//package com.fiap.infrastructure.event.listener;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import contracts.events.PagamentoAprovadoEvent;
//import com.fiap.core.dataaccess.PedidoDataAccess;
//import com.fiap.core.event.PedidoPagoEvent;
//import com.fiap.core.event.publisher.DomainEventPublisher;
//import com.fiap.core.usecases.pedidos.atualizar.AtualizarStatusPedidoInputBoundary;
//import com.fiap.core.usecases.pedidos.atualizar.AtualizarStatusPedidoInputData;
//import io.smallrye.reactive.messaging.annotations.Blocking;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import org.eclipse.microprofile.reactive.messaging.Incoming;
//import org.jboss.logging.Logger;
//
//@ApplicationScoped
//public class PagamentoAprovadoListener {
//
//    private static final Logger LOG =
//            Logger.getLogger(PagamentoAprovadoListener.class);
//
//    @Inject
//    AtualizarStatusPedidoInputBoundary atualizarStatusBoundary;
//
//    @Inject
//    PedidoDataAccess pedidoDataAccess;
//
//    @Inject
//    DomainEventPublisher eventPublisher;
//
//    @Inject
//    ObjectMapper objectMapper;
//
//    @Incoming("pagamento-aprovado-in")
//    @Blocking
//    public void onPagamentoAprovado(String payload) {
//        try {
//            PagamentoAprovadoEvent event =
//                    objectMapper.readValue(payload, PagamentoAprovadoEvent.class);
//
//            LOG.infof(
//                    "💰 Pagamento aprovado recebido | pedidoId=%s",
//                    event.pedidoId()
//            );
//
//            pedidoDataAccess.obterPorId(event.pedidoId())
//                    .filter(pedido -> !pedido.isPago())
//                    .ifPresent(pedido -> {
//
//                        atualizarStatusBoundary.marcarComoPago(
//                                new AtualizarStatusPedidoInputData(event.pedidoId())
//                        );
//
//                        eventPublisher.publish(
//                                PedidoPagoEvent.of(
//                                        pedido.getId(),
//                                        pedido.getRestauranteId()
//                                )
//                        );
//                    });
//
//        } catch (Exception e) {
//            // ⚠️ NÃO derrube o consumer Kafka
//            LOG.error("Erro ao processar pagamento.aprovado", e);
//        }
//    }
//}
