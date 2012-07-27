#include "Hasar715CLRConfig.h"
#include "EventosHasar715.h"
#include "Hasar715CLRConfigEnumFuncs.h"

Hasar715CLRConfig::Hasar715CLRConfig(ImpresorFiscal *imp, Logger *log): impresor(imp), logger(log){ }

void Hasar715CLRConfig::EstablecerPuertoSerie(int puerto){
	logger -> Logf ("Estableciendo puerto serie: %i", puerto);
	impresor->EstablecerPuertoSerie (puerto, 9600);
}

void Hasar715CLRConfig::EstablecerManejadorDeEventos(){
	logger->Log("Estableciendo manejador y capturador de eventos");
	EventosHasar715 *E = new EventosHasar715(impresor, logger);
	impresor->EstablecerManejadorDeEventos (E);
}

void Hasar715CLRConfig::EstablecerInterlineadoDeImpresion(int value){
	logger -> Logf ("Estableciendo interlineado de impresion: %i", value);
	impresor->EstablecerInterlineado (value);
	logger -> Logf ("INTERLINEADO: %i", impresor->ObtenerInterlineado ());
}

void Hasar715CLRConfig::ObtenerEstadoInterno(){
	logger -> Log ("Obteniendo estado interno de impresor");
	// Estado Interno del Impresor
	ImpresorFiscal::RTA_EstadoInternoImpresor R;
			
	impresor->EstadoInternoImpresor (&R);
	logger -> Log ("ESTADO INTERNO IMPRESOR (PL23F");
	logger -> Logf ("Estado:      %i", R.Estado);
	logger -> Logf ("Descripción: %s", R.Descripcion.c_str ());
}

void Hasar715CLRConfig::ObtenerDatosDeInicializacion(){
	logger -> Log ("Obteniendo datos de inicializacion");
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
	logger -> Log ("Obteniendo datos de memoria de trabajo");
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

void Hasar715CLRConfig::ObtenerReporteStatusImpresor(){
	logger -> Log ("REPORTE DE BITS DE PRINTER_STATUS");
	for (unsigned i = 0; i < 15; i++)
	{
		try
		{
			if (impresor->EstadoImpresor ((ImpresorFiscal::EstadosImpresor)i, false))
				logger -> Logf ("\tBit %2d (%-40.40s)", i, impresor->DescripcionEstadoImpresor ((ImpresorFiscal::EstadosImpresor)i).c_str ());
		}
		catch(Excepcion &e)
		{
			if (e.Codigo != Excepcion::IMPRESOR_FISCAL_ERROR_BIT_NO_VALIDO)
				throw;
		}
	}
}

void Hasar715CLRConfig::ObtenerReporteStatusFiscal(){
	logger -> Log ("REPORTE DE BITS DE FISCAL_STATUS");
	for (unsigned i = 0; i < 15; i++)
	{
		try
		{
			if (impresor->EstadoFiscal ((ImpresorFiscal::EstadosFiscal)i, false))
				logger -> Logf ("\tBit %2d (%-40.40s)", i, impresor->DescripcionEstadoFiscal ((ImpresorFiscal::EstadosFiscal)i).c_str ());
		}
		catch(Excepcion &e)
		{
			if (e.Codigo != Excepcion::IMPRESOR_FISCAL_ERROR_BIT_NO_VALIDO)
				throw;
		}
	}
}

void Hasar715CLRConfig::ObtenerUltimosDocumentos(){
	logger -> Log ("Obteniendo ultimos documentos");
	unsigned UltimoDFBC   = impresor->UltimoDocumentoFiscalBC ();
	unsigned UltimoDFA    = impresor->UltimoDocumentoFiscalA ();
	unsigned UltimaNCBC   = impresor->UltimaNotaCreditoBC ();
	unsigned UltimaNCA    = impresor->UltimaNotaCreditoA ();
			
	unsigned UltimoRemito = 0;
	try
	{
		UltimoRemito = impresor->UltimoRemito ();
	}

	catch(Excepcion &e)
	{
		if (e.Codigo == Excepcion::IMPRESOR_FISCAL_ERROR_NO_IMPLEMENTADO)
			logger -> Log ("Método UltimoRemito No Soportado por el modelo actual\n");
	}

	logger -> Log ("REPORTE DE Nº DE ULTIMOS DOCUMENTOS");
	logger -> Logf ("Ultimo DF BC:  %8lu", UltimoDFBC);
	logger -> Logf ("Ultimo DF A:   %8lu", UltimoDFA);
	logger -> Logf ("Ultima NC BC:  %8lu", UltimaNCBC);
	logger -> Logf ("Ultima NC A:   %8lu", UltimaNCA);
	logger -> Logf ("Ultimo Remito: %8lu", UltimoRemito);
}

void Hasar715CLRConfig::EstablecerFechaHora(char *fecha, char *hora){
	logger -> Log ("Estableciendo fecha y hora");

	ImpresorFiscal::FECHA F;
	ImpresorFiscal::HORA H;

	logger -> Log ("Obteniendo la Fecha y Hora antes de Re-programar");
	impresor->ObtenerFechaHoraFiscal (F, H);
	logger -> Log ("Fecha y Hora Anterior del CF");
	logger -> Logf ("Fecha: %02u/%02u/%04u - Hora: %02u:%02u:%02u",
				F.dia (), F.mes (), F.anio (),
				H.hora (), H.minutos (), H.segundos ());

	logger -> Log ("Re-programando la Fecha y Hora");
	ImpresorFiscal::FECHA FNueva;
	ImpresorFiscal::HORA HNueva;
	FNueva = ImpresorFiscal::FECHA (fecha);
	HNueva = ImpresorFiscal::HORA (hora);
	impresor->EstablecerFechaHoraFiscal (FNueva, HNueva);

	logger -> Log ("Obteniendo la Fecha y Hora después de Re-programar");
	impresor->ObtenerFechaHoraFiscal (F, H);
	logger -> Log ("Fecha y Hora Nueva del CF (Programada)");
	logger -> Logf ("Fecha: %02u/%02u/%04u - Hora: %02u:%02u:%02u",
				F.dia (), F.mes (), F.anio (),
				H.hora (), H.minutos (), H.segundos ());
}

void Hasar715CLRConfig::ObtenerConfiguracion(){
	logger -> Logf ("Obteniendo configuracion");
	ImpresorFiscal::RTA_ObtenerConfiguracion R;
			
	logger -> Log ("EJECUTANDO COMANDO OBTENER CONFIGURACION");
	impresor->ObtenerConfiguracion (&R);

	logger -> Log ("REPORTE DE CONFIGURACION DEL CF");
	logger -> Logf ("LimiteConsumidorFinal:         %.2f", R.LimiteConsumidorFinal);
	logger -> Logf ("LimiteTicketFactura:           %.2f", R.LimiteTicketFactura);
	logger -> Logf ("PorcentajeIVANoInscripto:      %.2f", R.PorcentajeIVANoInscripto);
	logger -> Logf ("TipoDeCopiasMaximo:			%c", R.TipoDeCopiasMaximo);
	logger -> Logf ("ImprimeCambio $0.00:           %s", R.ImprimeCambio ? "SI" : "NO");
	logger -> Logf ("ImprimeLeyendasOpcionales:     %s", R.ImprimeLeyendasOpcionales ? "SI" : "NO");
	logger -> Logf ("TipoDeCorte:                   %c", R.TipoDeCorte);
	logger -> Logf ("ImprimeMarco:                  %s", R.ImprimeMarco ? "SI" : "NO");
	logger -> Logf ("ReImprimeDocumentos:           %s", R.ReImprimeDocumentos ? "SI" : "NO");
	logger -> Logf ("DescripcionDelMedioDePago:     %s", R.DescripcionDelMedioDePago.c_str());
	logger -> Logf ("Sonido:                        %s", R.Sonido ? "SI" : "NO");
	logger -> Logf ("AltoHoja:                      %c", R.AltoHoja);
	logger -> Logf ("AnchoHoja:                     %c", R.AnchoHoja);
	logger -> Logf ("EstacionImpresionReportesXZ:   %c", R.EstacionImpresionReportesXZ);
	logger -> Logf ("ModoImpresion:                 %c", R.ModoImpresion);
	logger -> Logf ("ChequeoDesbordeCompleto:       %s", R.ChequeoDesbordeCompleto ? "SI" : "NO");
	logger -> Logf ("ChequeoTapaAbierta:			%s", R.ChequeoTapaAbierta ? "SI" : "NO");
}

void Hasar715CLRConfig::ConfigurarControlador(char *parametro, char *valor){
	logger -> Logf("Configurando controlador parametro: %s valor: %s", string(parametro), string(valor));
	impresor->ConfigurarControlador (Hasar715CLRConfigEnumFuncs::ObtenerParamConfiguracion(parametro), string(valor));
}

void Hasar715CLRConfig::ConfigurarControlador(bool Imprimir, bool Defaults, double LimiteConsumidorFinal, double LimiteTicketFactura,
	double PorcentajeIVANoInscripto, char *CopiasMaximo, bool ImprimeCambio, bool ImprimeLeyendasOpcionales, char *TipoCorte,
	bool ImprimeMarco, bool ReImprimeDocumentos, char *DescripcionMedioDePago, bool Sonido, char *AltoDeHoja, char *AnchoDeHoja,
	char *TipoEstacion, char *TipoModoImpresion){

	logger -> Log("Configurando controlador completo");

	ImpresorFiscal::NumerosDeCopias TipoDeCopiasMaximo = Hasar715CLRConfigEnumFuncs::ObtenerParamNumeroCopias(CopiasMaximo);
	ImpresorFiscal::TiposDeCorteDePapel TipoDeCorte = Hasar715CLRConfigEnumFuncs::ObtenerParamTipoCorte(TipoCorte);
	std::string DescripcionDelMedioDePago = string(DescripcionMedioDePago);
	ImpresorFiscal::TiposDeAltoHoja AltoHoja = Hasar715CLRConfigEnumFuncs::ObtenerParamAltoHoja(AltoDeHoja);
	ImpresorFiscal::TiposDeAnchoHoja AnchoHoja = Hasar715CLRConfigEnumFuncs::ObtenerParamAnchoHoja(AnchoDeHoja);
	ImpresorFiscal::TiposDeEstacion EstacionImpresionReportesXZ = Hasar715CLRConfigEnumFuncs::ObtenerParamTipoEstacion(TipoEstacion);
	ImpresorFiscal::TiposDeModoImpresion ModoImpresion = Hasar715CLRConfigEnumFuncs::ObtenerParamTipoModoImpresion(TipoModoImpresion);

	impresor->ConfigurarControladorCompleto (Imprimir, Defaults,
			true ? &LimiteConsumidorFinal		: NULL,
			true ? &LimiteTicketFactura			: NULL,
			true ? &PorcentajeIVANoInscripto	: NULL,
			true ? &TipoDeCopiasMaximo			: NULL,
			true ? &ImprimeCambio				: NULL,
			true ? &ImprimeLeyendasOpcionales	: NULL,
			true ? &TipoDeCorte					: NULL,
			true ? &ImprimeMarco				: NULL,
			true ? &ReImprimeDocumentos			: NULL,
			true ? &DescripcionDelMedioDePago	: NULL,
			true ? &Sonido						: NULL,
			true ? &AltoHoja					: NULL,
			true ? &AnchoHoja					: NULL,
			true ? &EstacionImpresionReportesXZ	: NULL,
			true ? &ModoImpresion				: NULL);	
}