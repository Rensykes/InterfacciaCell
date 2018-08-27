(deffunction get-number-of-facts-by-name (?names)
	(bind ?facts 0)
	(progn$ (?f (get-fact-list))
		(if (eq (fact-relation ?f) ?names)
			then (bind ?facts (+ ?facts 1))))
	(return ?facts)
)

(deffunction controllo-calcolabilita ()
	(bind ?factsCellula (find-all-facts ((?c cellula)) TRUE))
	(if (= (length ?factsCellula) 0) then
		(bind ?factsSintomo (find-all-facts ((?s sintomo)) TRUE))
		(if (= (length ?factsSintomo) 0) then
			(bind ?factsFamiglia (find-all-facts ((?f famiglia)) TRUE))
			(if (= (length ?factsFamiglia) 0) then
				(bind ?factsScoperta (find-all-facts ((?scp scoperta)) TRUE))
				(if (= (length ?factsScoperta) 0) then
					(bind ?factsPrickTest (find-all-facts ((?pt prick-test)) TRUE))
					(if (= (length ?factsPrickTest) 0) then
						(bind ?factsRinomanometria (find-all-facts ((?c rinomanometria)) TRUE))
						(if (= (length ?factsRinomanometria) 0) then
							(assert (diagnosi (nome "non calcolabile") (informazioni "Sintomatologia assente" "Anamnesi familiare assente" "Esame del medico assente" "Prick-test assente" "Citologia assente")))
						)
					)
				)
			)
		)
	)
)