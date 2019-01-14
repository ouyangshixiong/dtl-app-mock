import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnterpriseAuthRecord } from 'app/shared/model/enterprise-auth-record.model';

@Component({
    selector: 'jhi-enterprise-auth-record-detail',
    templateUrl: './enterprise-auth-record-detail.component.html'
})
export class EnterpriseAuthRecordDetailComponent implements OnInit {
    enterpriseAuthRecord: IEnterpriseAuthRecord;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ enterpriseAuthRecord }) => {
            this.enterpriseAuthRecord = enterpriseAuthRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
