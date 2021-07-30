(ns estoque.weekone)

(def customer 
  {:nome "Thiago", 
   :cpf "05965203365", 
   :email "nmariano85@gmail.com"})

(def credit-card 
  {:numero "5522885574986225", 
   :cvv 999, 
   :validade "07/2029", 
   :limite 1000})

(def compras
  [{:data            "2021-07-18 0:00"
    :valor           50.00
    :estabelecimento "Burcaneiros"
    :categoria       "Alimentação"}
   {:data            "2021-07-19 8:00"
    :valor           730.00
    :estabelecimento "Burcaneiros"
    :categoria       "Alimentação"}
   {:data            "2021-07-29 8:00"
    :valor           150.00
    :estabelecimento "Agenda Edu"
    :categoria       "Educação"}
   {:data            "2021-06-29 8:00"
    :valor           1205.00
    :estabelecimento "Khan academy"
    :categoria       "Educação"}
   {:data            "2021-06-15 9:00"
    :valor           250.00
    :estabelecimento "Sirio"
    :categoria       "Saúde"}
   {:data            "2021-05-29 8:00"
    :valor           150.99
    :estabelecimento "Libanes"
    :categoria       "Saúde"}])

(def compra-finalizada (assoc customer :cartao (assoc credit-card :compras compras)))

(defn agrupar-categoria [compra]
  (->> compra
       :cartao
       :compras
       (group-by :categoria)))

(defn preco-compra [compra]
  (:valor compra) )

(defn total-compras
  [compras]
  (reduce + (map preco-compra compras)))

(defn valor-total-categoria
  [[categoria compras]]
  [categoria (total-compras compras)])

(println "Compras realizadas")
(println (-> compra-finalizada
             :cartao
             :compras))
(println)

(println "Compras em R$ por categoria")
(println (map valor-total-categoria (agrupar-categoria compra-finalizada)))