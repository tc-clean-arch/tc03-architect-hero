//package com.fiap.infrastructure.event.listener;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fiap.core.usecases.pedidos.atualizar.AtualizarStatusPedidoInputBoundary;
//import com.fiap.core.usecases.pedidos.atualizar.AtualizarStatusPedidoInputData;
//import contracts.events.PagamentoPendenteEvent;
//import io.smallrye.reactive.messaging.annotations.Blocking;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import org.eclipse.microprofile.reactive.messaging.Incoming;
//import org.jboss.logging.Logger;
//
//@ApplicationScoped
//public class PagamentoPendenteListener {
//
//    private static final Logger LOG =
//            Logger.getLogger(PagamentoPendenteListener.class);
//
//    @Inject
//    AtualizarStatusPedidoInputBoundary atualizarStatusBoundary;
//
//    @Inject
//    ObjectMapper objectMapper;
//
//    @Incoming("pagamento-pendente-in")
//    @Blocking
//    public void onPagamentoPendente(String payload) {
//        try {
//            PagamentoPendenteEvent event =
//                    objectMapper.readValue(payload, PagamentoPendenteEvent.class);
//
//            LOG.warnf(
//                    "⏳ Pagamento pendente recebido | pedidoId=%s | motivo=%s",
//                    event.pedidoId(),
//                    event.motivo()
//            );
//
//            atualizarStatusBoundary.marcarComoPendente(
//                    new AtualizarStatusPedidoInputData(event.pedidoId())
//            );
//
//            LOG.warnf(
//                    "🔄 Pedido atualizado para PENDENTE_PAGAMENTO | pedidoId=%s",
//                    event.pedidoId()
//            );
//
//        } catch (Exception e) {
//            // ⚠️ NÃO derrubar o consumer Kafka
//            LOG.error("Erro ao processar pagamento.pendente", e);
//        }
//    }
//}
