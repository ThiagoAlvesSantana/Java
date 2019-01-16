package br.com.model.bean;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = CarroBean.TABLE)
public class CarroBean implements Serializable {
    
    public static final String TABLE = "TAB_CARRO";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cd_carro;

    private String nm_marca;
    private String nm_modelo;

    public Integer getCd_carro() {
        return cd_carro;
    }

    public void setCd_carro(Integer cd_carro) {
        this.cd_carro = cd_carro;
    }

    public String getNm_marca() {
        return nm_marca;
    }

    public void setNm_marca(String nm_marca) {
        this.nm_marca = nm_marca;
    }

    public String getNm_modelo() {
        return nm_modelo;
    }

    public void setNm_modelo(String nm_modelo) {
        this.nm_modelo = nm_modelo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.cd_carro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarroBean other = (CarroBean) obj;
        if (!Objects.equals(this.cd_carro, other.cd_carro)) {
            return false;
        }
        return true;
    }
}
