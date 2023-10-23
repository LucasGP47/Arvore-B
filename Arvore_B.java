package arvores;

public class Arvore_B {

	private class Nodo{
		
		private int chave;
		private Nodo dir, esq;
		
		public Nodo(int item) {
			this.chave = item;
			dir = esq = null;
		}	
	}
	
	private Nodo raiz; 
    
    public Arvore_B() {
        this.raiz = null;
    }
    
    public void inserir(int chave) {
        if (raiz == null) {
            raiz = new Nodo(chave);
        } else {
        	inserirElemento(chave);
        }
    }

    private void inserirElemento(int chave) {
        Nodo nodoAtual = raiz;
        boolean inserido = false;

        while (inserido == false) {
            if (nodoAtual.chave > chave) {
                if (nodoAtual.esq == null) {
                    nodoAtual.esq = new Nodo(chave);
                    inserido = true;
                } else {
                    nodoAtual = nodoAtual.esq;
                }
            } else {
                if (nodoAtual.dir == null) {
                    nodoAtual.dir = new Nodo(chave);
                    inserido = true;
                } else {
                    nodoAtual = nodoAtual.dir;
                }
            }
        }
    }

    public void removerElemento(int chave) {
        Nodo pai = null;
        Nodo nodoAtual = raiz;
        boolean encontrado = false;

        while (nodoAtual != null) {
            if (nodoAtual.chave == chave) {
                encontrado = true;
                break;
            }
            pai = nodoAtual;
            if (nodoAtual.chave > chave) {
                nodoAtual = nodoAtual.esq;
            } else {
                nodoAtual = nodoAtual.dir;
            }
        }

        if (encontrado == true) {
            if (nodoAtual.esq == null || nodoAtual.dir == null) {             
                Nodo filho = (nodoAtual.esq != null) ? nodoAtual.esq : nodoAtual.dir;

                if (pai == null) {
                    raiz = filho;
                } else if (nodoAtual == pai.esq) {
                    pai.esq = filho;
                } else {
                    pai.dir = filho;
                }
            } else {               
                Nodo sucessor = encontrarSucessor(nodoAtual);
                int temp = sucessor.chave;               
                pai = nodoAtual; 
                nodoAtual = nodoAtual.dir;
                while (nodoAtual.esq != null) {
                    pai = nodoAtual;
                    nodoAtual = nodoAtual.esq;
                }

                nodoAtual.chave = temp;
                if (pai == nodoAtual) {
                    pai.dir = nodoAtual.dir;
                } else {
                    pai.esq = nodoAtual.dir;
                }
            }        
        }   
    }

    private Nodo encontrarSucessor(Nodo nodo) {
        Nodo atual = nodo.dir;
        Nodo pai = nodo;
        while (atual.esq != null) {
            pai = atual;
            atual = atual.esq;
        }
        if (pai != nodo) {
            pai.esq = atual.dir;
            atual.dir = nodo.dir;
        }
        return atual;
    }
    
    public String buscar(int chave) {
    	if (buscarElemento(chave)) {
    		return "Chave " + chave + " encontrada!";
    	} else {
    		return "Chave " + chave + " não encontrada!";
    	}
    }

    private boolean buscarElemento(int chave) {
        Nodo nodoAtual = raiz;       
        while (nodoAtual != null) {
            if (nodoAtual.chave == chave) {
                return true; 
            } else if (nodoAtual.chave > chave) {
                nodoAtual = nodoAtual.esq;
            } else {
                nodoAtual = nodoAtual.dir;
            }
        }
        return false;
    }   
    
    public void mostrarEmOrdem() {
		mostrarOrdenado(raiz);
	}
	
	private void mostrarOrdenado(Nodo raiz) {
		if(raiz != null) {
			mostrarOrdenado(raiz.esq);
			System.out.println(raiz.chave);
			mostrarOrdenado(raiz.dir);
		}
	}

}
