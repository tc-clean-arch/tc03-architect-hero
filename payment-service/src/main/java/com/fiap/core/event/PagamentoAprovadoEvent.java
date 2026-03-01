//package com.fiap.core.event;
//
//import java.time.Instant;
//import java.util.UUID;
//
//public record PagamentoAprovadoEvent(
//        UUID pedidoId,
//        UUID clienteId,
//        int valor,
//        Instant ocorridoEm
//) {
//
//    public static PagamentoAprovadoEvent of(
//            UUID pedidoId,
//            UUID clienteId,
//            int valor
//    ) {
//        return new PagamentoAprovadoEvent(
//                pedidoId,
//                clienteId,
//                valor,
//                Instant.now()
//        );
//    }
//}
