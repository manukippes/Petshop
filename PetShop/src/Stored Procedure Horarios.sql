delimiter $$
drop procedure if exists getHorariosDisponibles $$


create procedure getHorariosDisponibles(diaSeleccionado Date)
begin
    
	drop temporary table if exists horariosDisponibles;
	drop temporary table if exists turnosDia;

	/*Creo una tabla con todos los horarios*/
	create temporary table horariosDisponibles (primary key (horario)) as 
										SELECT 
										* 
										FROM horarios;
										
	/*Creo una tabla con los horarios del dia y su duracion*/                                    
	create temporary table turnosDia as 
										(SELECT 
											hora,addtime(hora,tiempo) duracion
										FROM
											turno t
												inner JOIN
											mascota m ON t.idMascota = m.idMascota
												inner JOIN
											tipo_mascota tm ON m.idTipoMascota = tm.idTipoMascota
											inner JOIN
											tipo_mascota_servicio tms ON tms.idTipoMascota = tm.idTipoMascota and tms.idServicio = t.idServicio
                                            where fecha = diaSeleccionado);    
    begin
		declare horaDesde Time default null;
		declare horaHasta Time default null;
    
		declare listo Tinyint default false;
		
        declare cursor1
			cursor for select * from turnosDia;
		
		declare continue handler for not found set listo = true;
		
		open cursor1;
		
		my_loop:
		LOOP
			fetch next from cursor1 into horaDesde, horaHasta;
			if listo then
				leave my_loop;
			else
				delete from horariosDisponibles where horario >= horaDesde and horario < horaHasta;
			end if;
		end Loop;
    end;
    
select * from horariosDisponibles;

end $$
delimiter ;

call getHorariosDisponibles('2018-01-01');



