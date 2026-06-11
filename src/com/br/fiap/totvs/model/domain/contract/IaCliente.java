package com.br.fiap.totvs.model.domain.contract;

import com.br.fiap.totvs.model.domain.entity.InsightComercial;
import com.br.fiap.totvs.model.domain.entity.Transcricao;


import java.util.List;

public interface IaCliente {

    //Qualquer mecanismo de ia deve receber uma trancrição e devolver uma lista de objetos de insightComercial

    List<InsightComercial> extrairInsghtComercial(Transcricao transcricao);
}
