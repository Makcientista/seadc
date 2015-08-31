;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; JESS RULES FOR SEADC  ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;(watch all)

(import main.java.br.unip.seadc.beans.Sintoma)
(import main.java.br.unip.seadc.beans.Diagnostico)

(deftemplate Sintoma		(declare (from-class Sintoma)))
(deftemplate Diagnostico	(declare (from-class Diagnostico)))

;; regras ;;
(defrule sopro-no-coracao
	(FatorDeRisco {nome == "infarto"})
	(FatorDeRisco {nome == "alcoolismo"})
	(FatorDeRisco {nome == "hipertensao"})
	(FatorDeRisco {nome == "usuario-de-drogas"})
	(FatorDeRisco {nome == "historico-familiar"})
	(Sintoma {nome == "pele-azulada"})
	(Sintoma {nome == "inchaco-ou-ganho-de-peso"})
	(Sintoma {nome == "falta-de-ar"})
	(Sintoma {nome == "tosse-cronica"})
	(Sintoma {nome == "figado-inchado"})
	(Sintoma {nome == "veias-do-pescoco-aumentadas"})
	(Sintoma {nome == "falta-de-apetite"})
	(Sintoma {nome == "transpiracao-intensa"})
	(Sintoma {nome == "dor-no-peito"})
	(Sintoma {nome == "tontura"})
	(Sintoma {nome == "desmaio"})
	=>
	(set-reset-globals nil)
	(bind ?*nome-doenca* "sopro-no-coracao")
	(add (new Diagnostico ?*nome-doenca* ?*recomendacao-sopro-no-coracao*))
)

(defrule cardiomiopatia
	(FatorDeRisco {nome == "diabetes"})
	(FatorDeRisco {nome == "alcoolismo"})
	(FatorDeRisco {nome == "gravidez"})
	(FatorDeRisco {nome == "obesidade"})
	(FatorDeRisco {nome == "usuario-de-drogas"})
	(FatorDeRisco {nome == "infeccoes-virais"})
	(FatorDeRisco {nome == "fumante"})
	(FatorDeRisco {nome == "historico-familiar"})
	(Sintoma {nome == "falta-de-ar"})
	(Sintoma {nome == "inchaco-partes-inferiores"})
	(Sintoma {nome == "inchaco-no-abdomen"})
	(Sintoma {nome == "tosse"})
	(Sintoma {nome == "fadiga"})
	(Sintoma {nome == "arritmia-cardiaca"})
	(Sintoma {nome == "tontura"})
	(Sintoma {nome == "vertigem"})
	(Sintoma {nome == "desmaio"})
	=>
	(set-reset-globals nil)
	(bind ?*nome-doenca* "cardiomiopatia")
	(add (new Diagnostico ?*nome-doenca* ?*recomendacao-cardiomiopatia*))
)

(defrule fibrilacao-atrial
	(FatorDeRisco {nome == "alcoolismo"})
	(FatorDeRisco {nome == "obesidade"})
	(FatorDeRisco {nome == "hipertensao"})
	(FatorDeRisco {nome == "historico-familiar"})
	(Sintoma {nome == "palpitacoes-no-peito"})
	(Sintoma {nome == "dor-no-peito"})
	(Sintoma {nome == "fraqueza"})
	(Sintoma {nome == "fadiga"})
	(Sintoma {nome == "vertigem"})
	(Sintoma {nome == "tontura"})
	(Sintoma {nome == "confusao"})
	(Sintoma {nome == "falta-de-ar"})
	(Sintoma {nome == "desmaio"})
	=>
	(set-reset-globals nil)
	(bind ?*nome-doenca* "fibrilacao-atrial")
	(add (new Diagnostico ?*nome-doenca* ?*recomendacao-fibrilacao-atrial*))
)

(defrule arritmia-cardiaca
	(FatorDeRisco {nome == "fumante"})
	(FatorDeRisco {nome == "usuario-de-drogas"})
	(Sintoma {nome == "falta-de-ar"})
	(Sintoma {nome == "sensacao-de-peso-no-peito"})
	(Sintoma {nome == "fraqueza"})
	(Sintoma {nome == "fadiga"})
	(Sintoma {nome == "dor-no-peito"})
	(Sintoma {nome == "palpitacoes-no-peito"})
	(Sintoma {nome == "desmaio"})
	=>
	(set-reset-globals nil)
	(bind ?*nome-doenca* "arritmia-cardiaca")
	(add (new Diagnostico ?*nome-doenca* ?*recomendacao-arritmia-cardiaca*))
)

(defrule hipertensao
	(FatorDeRisco {nome == "colesterol-alto"})
	(FatorDeRisco {nome == "obesidade"})
	(FatorDeRisco {nome == "fumante"})
	(FatorDeRisco {nome == "alcoolismo"})
	(FatorDeRisco {nome == "diabetes"})
	(Sintoma {nome == "dor-no-peito"})
	(Sintoma {nome == "dor-de-cabeca"})
	(Sintoma {nome == "tontura"})
	(Sintoma {nome == "zumbido-no-ouvido"})
	(Sintoma {nome == "fraqueza"})
	(Sintoma {nome == "visao-embacada"})
	(Sintoma {nome == "sangramento-nasal"})
	=>
	(set-reset-globals nil)
	(bind ?*nome-doenca* "hipertensao")
	(add (new Diagnostico ?*nome-doenca* ?*recomendacao-hipertensao*))
)

;; variáveis globais ;;
;;(defglobal ?*nome-doenca* = nil)

;; constantes ;;
(defglobal ?*recomendacao-sopro-no-coracao*  = 
	
	"O sopro no coração é um som extra ou diferente durante o batimento cardíaco. Sopro no coração varia de bem fraco a bastante alto. O sopro no coração não é uma doença, mas um som que o médico escuta com o estetoscópio. Ele pode ser normal ou um sinal de que alguma coisa pode estar errada. A maioria dos sopros cardíacos não é sinal de algo danoso. Alguns são sinal de problemas no coração, especialmente se outros Sintomas de problemas cardíacos estiverem presentes. 
	Tipos de sopro no coração 

	Sopro cardíaco inocente ou funcional 
	Uma pessoa como sopro cardíaco inocente ou funcional tem um coração normal e geralmente não há sinal ou Sintoma de problema cardíaco. Sopro cardíaco inocente é comum em crianças saudáveis."
)

(defglobal ?*recomendacao-cardiomiopatia*  = 
	
	"A cardiomiopatia é uma doença muito difícil de se obter uma recomendação válida."
)

(defglobal ?*recomendacao-fibrilacao-atrial*  = 
	
	"A Fibrilacao atrial é uma doença muito difícil de se obter uma recomendação válida."
)

(defglobal ?*recomendacao-arritmia-cardiaca*  = 
	
	"A Arritmia cardíaca é uma doença muito difícil de se obter uma recomendação válida."
)

(defglobal ?*recomendacao-hipertensao*  = 
	
	"A Hipertensão é uma doença muito difícil de se obter uma recomendação válida."
)