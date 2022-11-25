package training.lab.product.manufacturers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.lab.product.manufacturers.exception.CrudException;
import training.lab.product.manufacturers.exception.ResourceNotFoundException;
import training.lab.product.manufacturers.model.Manufacturer;
import training.lab.product.manufacturers.repository.ManufacturerRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> getManufacturers() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer getManufacturer(Long manufacturerId) {
        if (null == manufacturerId || manufacturerId <= 0) {
            throw new CrudException("Manufacturer Id Is Invalid");
        }
        Manufacturer manufacturer = null;
        try {
            manufacturer = manufacturerRepository.findById(manufacturerId).get();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Manufacturer Record Id: " + manufacturerId + " Not Found In The Database");
        }
        return manufacturer;
    }

    @Transactional
    public Manufacturer saveManufacturer(Manufacturer manufacturer) {
        if (null == manufacturer) {
            throw new CrudException("Request Parameters Not Found In The Request Body");
        }
        if (!(null == manufacturer.getManufacturerId())) {
            throw new CrudException("Remove the Manufacturer Id From The Request Body. It Will Be Auto Generated By The System");
        }
        Manufacturer newManufacturer = null;
        try {
            newManufacturer = manufacturerRepository.save(manufacturer);
        } catch(Exception ex) {
            throw new CrudException("Error While Inserting The Record In To The Database");
        }
        return newManufacturer;
    }

    @Transactional
    public Manufacturer updateManufacturer(Manufacturer manufacturer) {
        if (null == manufacturer) {
            throw new CrudException("Request Parameters Not Found In The Request Body");
        }
        if (null == manufacturer.getManufacturerId()) {
            throw new CrudException("Manufacturer Id Is Missing In The Request Body");
        }
        Manufacturer updatedManufacturer = null;
        try {
            updatedManufacturer = manufacturerRepository.save(manufacturer);
        } catch(Exception ex) {
            throw new CrudException("Error While Updating The Record In To The Database");
        }
        return updatedManufacturer;
    }

    @Transactional
    public void deleteManufacturer(Long manufacturerId) {
        if (null == manufacturerId || manufacturerId <= 0) {
            throw new CrudException("Manufacturer Id Is Invalid");
        }
        try {
            manufacturerRepository.deleteById(manufacturerId);
        } catch(Exception ex) {
            throw new ResourceNotFoundException("Manufacturer Record Id: " + manufacturerId + " Not Found In The Database");
        }
    }
}
