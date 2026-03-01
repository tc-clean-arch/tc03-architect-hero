//package com.fiap.core.event;
//
//import java.time.Instant;
//import java.util.UUID;
//
//public record PagamentoPendenteEvent(
//        UUID pedidoId,
//        UUID clienteId,
//        int valor,
//        String motivo,
//        int tentativas,
//        Instant ocorridoEm
//) {
//
//    public static PagamentoPendenteEvent of(
//            UUID pedidoId,
//            UUID clienteId,
//            int valor,
//            String motivo,
//            int tentativas
//    ) {
//        return new PagamentoPendenteEvent(
//                pedidoId,
//                clienteId,
//                valor,
//                motivo,
//                tentativas,
//                Instant.now()
//        );
//    }
//}
