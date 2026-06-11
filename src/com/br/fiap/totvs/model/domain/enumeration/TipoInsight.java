package com.br.fiap.totvs.model.domain.enumeration;

public enum TipoInsight {
    CHURN, // Risco de o cliente cancelar o contrato
    UPSELL, // Oportunidade de vender mais do mesmo produto (módulos extras)
    CROSS_SELL, // Oportunidade de vender produtos de outras linhas da TOTVS
    CONCORRENTE, // Citação de empresas rivais (Senior, SAP, Linx, etc.)
    SENTIMENTO // Percepção geral do humor/satisfação do cliente
}
