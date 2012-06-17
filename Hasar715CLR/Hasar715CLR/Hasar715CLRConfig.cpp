#include "Hasar715CLRConfig.h"
#include "EventosHasar715.h"

Hasar715CLRConfig::Hasar715CLRConfig(ImpresorFiscal *imp, Logger *log): impresor(imp), logger(log){ }

void Hasar715CLRConfig::EstablecerPuertoSerie(int puerto){
	logger -> Logf ("Estableciendo puerto serie: %d", puerto);
	impresor->EstablecerPuertoSerie (puerto, 9600);
}

void Hasar715CLRConfig::EstablecerManejadorDeEventos(){
	logger->Log("Estableciendo manejador y capturador de eventos");
	EventosHasar715 *E = new EventosHasar715(impresor, logger);
	impresor->EstablecerManejadorDeEventos (E);
}

void Hasar715CLRConfig::EstablecerInterlineadoDeImpresion(int value){
	logger -> Logf ("Estableciendo interlineado de impresion: %d", value);
	impresor->EstablecerInterlineado (value);
	logger -> Logf ("INTERLINEADO: %d", impresor->ObtenerInterlineado ());
}

void Hasar715CLRConfig::ObtenerEstadoInterno(){
	// Estado Interno del Impresor
	ImpresorFiscal::RTA_EstadoInternoImpresor R;
			
	impresor->EstadoInternoImpresor (&R);
	logger -> Log ("ESTADO INTERNO IMPRESOR (PL23F");
	logger -> Logf ("Estado:      %d", R.Estado);
	logger -> Logf ("Descripción: %s", R.Descripcion.c_str ());
}

void Hasar715CLRConfig::ObtenerDatosDeInicializacion(){
	ImpresorFiscal::RTA_ObtenerDatosDeInicializacion R;
			
	impresor->ObtenerDatosDeInicializacion (&R);
					
	logger -> Log ("DATOS DE INICIALIZACIÓN");
	logger -> Logf ("Nombre:   %s",				R.RazonSocial.c_str ());
	logger -> Logf ("C.U.I.T.: %s",				R.NroCUIT.c_str ());
	logger -> Logf ("Nº Serie: %s",				R.NroSerie.c_str ());
	logger -> Logf ("FechaIni: %02u/%02u/%04u", R.FechaIncializacion.dia (), R.FechaIncializacion.mes (), R.FechaIncializacion.anio ());
	logger -> Logf ("Nº POS:   %s",				R.NroPOS.c_str ());
	logger -> Logf ("IngBr:    %s",				R.CodIngBrutos.c_str ());
	logger -> Logf ("FeIniAct: %02u/%02u/%04u", R.FechaIniActividades.dia (), R.FechaIniActividades.mes (), R.FechaIniActividades.anio ());
	logger -> Logf ("Resp.IVA: %c",				R.RespIVA);
}

void Hasar715CLRConfig::ObtenerDatosMemoriaDeTrabajo(){
	ImpresorFiscal::RTA_ObtenerDatosMemoriaDeTrabajo R;
			
	impresor->ObtenerDatosMemoriaDeTrabajo (&R);
					
	logger -> Log ("Datos de Memoria de Trabajo");
	logger -> Logf ("CantidadDFCancelados             = %u",		R.CantidadDFCancelados			);
	logger -> Logf ("CantidadDNFEmitidos              = %u",		R.CantidadDNFEmitidos			);
	logger -> Logf ("CantidadDFEmitidos               = %u",		R.CantidadDFEmitidos			);
	logger -> Logf ("UltimoDocFiscalBC                = %lu",		R.UltimoDocFiscalBC				);
	logger -> Logf ("UltimoDocFiscalA                 = %lu",		R.UltimoDocFiscalA				);
	logger -> Logf ("MontoVentasDocFiscal             = %.2f",		R.MontoVentasDocFiscal			);
	logger -> Logf ("MontoIVADocFiscal                = %.2f",		R.MontoIVADocFiscal				);
	logger -> Logf ("MontoImpInternosDocFiscal        = %.2f",		R.MontoImpInternosDocFiscal		);
	logger -> Logf ("MontoPercepcionesDocFiscal       = %.2f",		R.MontoPercepcionesDocFiscal	);
	logger -> Logf ("MontoIVANoInscriptoDocFiscal     = %.2f",		R.MontoIVANoInscriptoDocFiscal	);
	logger -> Logf ("UltimaNotaCreditoBC              = %lu",		R.UltimaNotaCreditoBC			);
	logger -> Logf ("UltimaNotaCreditoA               = %lu",		R.UltimaNotaCreditoA			);
	logger -> Logf ("MontoVentasNotaCredito           = %.2f",		R.MontoVentasNotaCredito		);
	logger -> Logf ("MontoIVANotaCredito              = %.2f",		R.MontoIVANotaCredito			);
	logger -> Logf ("MontoImpInternosNotaCredito      = %.2f",		R.MontoImpInternosNotaCredito	);
	logger -> Logf ("MontoPercepcionesNotaCredito     = %.2f",		R.MontoPercepcionesNotaCredito	);
	logger -> Logf ("MontoIVANoInscriptoNotaCredito   = %.2f",		R.MontoIVANoInscriptoNotaCredito);
	logger -> Logf ("UltimoRemito                     = %lu",		R.UltimoRemito					);
	logger -> Logf ("CantidadNCCanceladas             = %u",		R.CantidadNCCanceladas			);
	logger -> Logf ("CantidadDFBCEmitidos             = %u",		R.CantidadDFBCEmitidos			);
	logger -> Logf ("CantidadDFAEmitidos              = %u",		R.CantidadDFAEmitidos			);
	logger -> Logf ("CantidadNCBCEmitidas             = %u",		R.CantidadNCBCEmitidas			);
	logger -> Logf ("CantidadNCAEmitidas              = %u",		R.CantidadNCAEmitidas			);
}

void Hasar715CLRConfig::CambiarResponsabilidadIVA(){
	logger -> Log ("Cambiando responsabilidad IVA");
	impresor->CambiarResponsabilidadIVA (ImpresorFiscal::CLASE_RESPONSABLE_INSCRIPTO);
}

void Hasar715CLRConfig::CambiarCodigoIngresosBrutos(char *codigo){
	logger -> Logf ("Cambiando codigo de ingresos brutos: %s", codigo);
	impresor->CambiarCodigoIngresosBrutos (string(codigo));
}

void Hasar715CLRConfig::CambiarFechaInicioActividades(char *fechaInicio){
	logger -> Logf ("Cambiando fecha inicio de actividades: %s", fechaInicio);
	ImpresorFiscal::FECHA F = fechaInicio;
	impresor->CambiarFechaInicioActividades (F);
}