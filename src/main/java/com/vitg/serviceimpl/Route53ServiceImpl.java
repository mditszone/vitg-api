package com.vitg.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.route53.AmazonRoute53;
import com.amazonaws.services.route53.model.Change;
import com.amazonaws.services.route53.model.ChangeAction;
import com.amazonaws.services.route53.model.ChangeBatch;
import com.amazonaws.services.route53.model.ChangeResourceRecordSetsRequest;
import com.amazonaws.services.route53.model.ResourceRecord;
import com.amazonaws.services.route53.model.ResourceRecordSet;
import com.amazonaws.services.s3.AmazonS3;
import com.vitg.dto.ResponseDTO;
import com.vitg.service.Route53Service;

@Service
public class Route53ServiceImpl implements Route53Service {

	@Autowired
	private AmazonRoute53 Route53;

	@Override
	public ResponseDTO createSubDomain(String subDomain) {
		ChangeResourceRecordSetsRequest changeResourceRecordSetsRequest = new ChangeResourceRecordSetsRequest();

		changeResourceRecordSetsRequest.setHostedZoneId( "Z02735052LOCWQJ23NMNO" );
		ChangeBatch changeBatch = new ChangeBatch();
		List<Change> changes = new ArrayList<Change>();
		Change aChange = new Change();
		aChange.setAction( ChangeAction.CREATE );
		ResourceRecordSet resourceRecordSet = new ResourceRecordSet();

		resourceRecordSet.setName( subDomain ); // use your own domain, OK?

		resourceRecordSet.setTTL( 300L );
		resourceRecordSet.setType( "A" );
		List<ResourceRecord> resourceRecords = new ArrayList<ResourceRecord>();
		ResourceRecord aRecord = new ResourceRecord();

		aRecord.setValue( "192.0.2.235" ); // use a real IP address, OK?

		resourceRecords.add( aRecord );
		resourceRecordSet.setResourceRecords( resourceRecords );
		aChange.setResourceRecordSet( resourceRecordSet );
		changes.add( aChange );
		changeBatch.setChanges( changes );
		changeBatch.setComment( "Just a test change..." );
		changeResourceRecordSetsRequest.setChangeBatch( changeBatch );

		Route53.changeResourceRecordSets( changeResourceRecordSetsRequest );

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setStatus(200);
		responseDTO.setMessage("subDomain created");
		return responseDTO;
		
	}

}
