#include "Hasar715CLRConfigEnumFuncs.h"

ImpresorFiscal::ParametrosDeConfiguracion Hasar715CLRConfigEnumFuncs::ObtenerParamConfiguracion(char *valor){	
	if(*valor == '4') return ImpresorFiscal::IMPRESION_CAMBIO;
	else if(*valor == '5') return ImpresorFiscal::IMPRESION_LEYENDAS;
	else if(*valor == '6') return ImpresorFiscal::CORTE_PAPEL;
	else if(*valor == '7') return ImpresorFiscal::IMPRESION_MARCO;
	else if(*valor == '8') return ImpresorFiscal::REIMPRESION_CANCELADOS;
	else if(*valor == '9') return ImpresorFiscal::COPIAS_DOCUMENTOS;
	else if(*valor == ':') return ImpresorFiscal::PAGO_SALDO;
	else if(*valor == ';') return ImpresorFiscal::SONIDO_AVISO;
	else if(*valor == '<') return ImpresorFiscal::ALTO_HOJA;
	else if(*valor == '=') return ImpresorFiscal::ANCHO_HOJA;
	else if(*valor == '>') return ImpresorFiscal::ESTACION_REPORTES_XZ;
	else if(*valor == '?') return ImpresorFiscal::MODO_IMPRESION;
	else if(*valor == '@') return ImpresorFiscal::CHEQUEO_DESBORDE;
	else if(*valor == 'A') return ImpresorFiscal::CHEQUEO_TAPA_ABIERTA;
}

ImpresorFiscal::NumerosDeCopias Hasar715CLRConfigEnumFuncs::ObtenerParamNumeroCopias(char *valor)
{
	if(*valor == '0') return ImpresorFiscal::NO_COPIAS;
	else if(*valor == '1') return ImpresorFiscal::ORIGINAL;
	else if(*valor == '2') return ImpresorFiscal::DUPLICADO;
	else if(*valor == '3') return ImpresorFiscal::TRIPLICADO;
	else if(*valor == '4') return ImpresorFiscal::CUADRUPLICADO;
}

ImpresorFiscal::TiposDeCorteDePapel Hasar715CLRConfigEnumFuncs::ObtenerParamTipoCorte(char *valor){
	if(*valor == 'F') return ImpresorFiscal::CORTE_TOTAL;
	else if(*valor == 'P') return ImpresorFiscal::CORTE_PARCIAL;
	else if(*valor == 'N') return ImpresorFiscal::NO_CORTE;
}

ImpresorFiscal::TiposDeAltoHoja Hasar715CLRConfigEnumFuncs::ObtenerParamAltoHoja(char *valor){
	if(*valor == 'M') return ImpresorFiscal::ALTO_REDUCIDO;
	else if(*valor == 'A') return ImpresorFiscal::ALTO_A4;
	else if(*valor == 'O') return ImpresorFiscal::ALTO_OFICIO;
	else if(*valor == 'C') return ImpresorFiscal::ALTO_CARTA;
}

ImpresorFiscal::TiposDeAnchoHoja Hasar715CLRConfigEnumFuncs::ObtenerParamAnchoHoja(char *valor){
	if(*valor == 'M') return ImpresorFiscal::ANCHO_REDUCIDO;
	else if(*valor == 'N') return ImpresorFiscal::ANCHO_NORMAL;
}

ImpresorFiscal::TiposDeEstacion Hasar715CLRConfigEnumFuncs::ObtenerParamTipoEstacion(char *valor){
	if(*valor == 'T') return ImpresorFiscal::ESTACION_TICKET;
	else if(*valor == 'S') return ImpresorFiscal::ESTACION_SLIP;
}

ImpresorFiscal::TiposDeModoImpresion Hasar715CLRConfigEnumFuncs::ObtenerParamTipoModoImpresion(char *valor){
	if(*valor == 'M') return ImpresorFiscal::USO_ESTACION_TICKET;
	else if(*valor == 'A') return ImpresorFiscal::NO_USO_ESTACION_TICKET;
}

ImpresorFiscal::DocumentosFiscales Hasar715CLRConfigEnumFuncs::ObtenerParamDocumentosFiscales(char *valor){
	
	if(*valor == 'A') return ImpresorFiscal::TICKET_FACTURA_A;
	else if(*valor == 'B') return ImpresorFiscal::TICKET_FACTURA_B;
	else if(*valor == '0') return ImpresorFiscal::FACTURA_A;
	else if(*valor == '1') return ImpresorFiscal::FACTURA_B;
	else if(*valor == '2') return ImpresorFiscal::TICKET_NOTA_DEBITO_A;
	else if(*valor == '3') return ImpresorFiscal::TICKET_NOTA_DEBITO_B;
	else if(*valor == 'D') return ImpresorFiscal::NOTA_DEBITO_A;
	else if(*valor == 'E') return ImpresorFiscal::NOTA_DEBITO_B;
	else if(*valor == 'a') return ImpresorFiscal::RECIBO_A;
	else if(*valor == 'b') return ImpresorFiscal::RECIBO_B;
	else if(*valor == 'T') return ImpresorFiscal::TICKET_C;
}

ImpresorFiscal::TiposDeDocumentoCliente Hasar715CLRConfigEnumFuncs::ObtenerParamTiposDeDocumentoCliente(char *valor){
	
	if(*valor == 'C') return ImpresorFiscal::TIPO_CUIT;
	else if(*valor == 'L') return ImpresorFiscal::TIPO_CUIL;
	else if(*valor == '0') return ImpresorFiscal::TIPO_LE;
	else if(*valor == '1') return ImpresorFiscal::TIPO_LC;
	else if(*valor == '2') return ImpresorFiscal::TIPO_DNI;
	else if(*valor == '3') return ImpresorFiscal::TIPO_PASAPORTE;
	else if(*valor == '4') return ImpresorFiscal::TIPO_CI;
	else if(*valor == ' ') return ImpresorFiscal::TIPO_NINGUNO;
}

ImpresorFiscal::TiposDeResponsabilidadesCliente Hasar715CLRConfigEnumFuncs::ObtenerParamTiposDeResponsabilidadesCliente(char *valor){
	
	if(*valor == 'I') return ImpresorFiscal::RESPONSABLE_INSCRIPTO;
	else if(*valor == 'N') return ImpresorFiscal::RESPONSABLE_NO_INSCRIPTO;
	else if(*valor == 'E') return ImpresorFiscal::RESPONSABLE_EXENTO;
	else if(*valor == 'A') return ImpresorFiscal::NO_RESPONSABLE;
	else if(*valor == 'C') return ImpresorFiscal::CONSUMIDOR_FINAL;
	else if(*valor == 'B') return ImpresorFiscal::BIENES_DE_USO;
	else if(*valor == 'T') return ImpresorFiscal::NO_CATEGORIZADO;
	else if(*valor == 'M') return ImpresorFiscal::MONOTRIBUTO;
	else if(*valor == 'S') return ImpresorFiscal::MONOTRIBUTO_SOCIAL;
	else if(*valor == 'V') return ImpresorFiscal::EVENTUAL;
	else if(*valor == 'W') return ImpresorFiscal::EVENTUAL_SOCIAL;
}