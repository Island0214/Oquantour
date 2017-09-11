package oquantour.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by island on 2017/6/11.
 */
@Entity
@Table(name = "testtable", schema = "oquantour")
public class TestTablePO {
    private Double testName;

    @Id
    @Column(name = "TestName")
    public Double getTestName() {
        return testName;
    }

    public void setTestName(Double testName) {
        this.testName = testName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestTablePO that = (TestTablePO) o;

        if (testName != null ? !testName.equals(that.testName) : that.testName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return testName != null ? testName.hashCode() : 0;
    }
}
