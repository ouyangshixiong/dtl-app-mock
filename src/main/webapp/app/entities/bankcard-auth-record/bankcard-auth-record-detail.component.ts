import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBankcardAuthRecord } from 'app/shared/model/bankcard-auth-record.model';

@Component({
    selector: 'jhi-bankcard-auth-record-detail',
    templateUrl: './bankcard-auth-record-detail.component.html'
})
export class BankcardAuthRecordDetailComponent implements OnInit {
    bankcardAuthRecord: IBankcardAuthRecord;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bankcardAuthRecord }) => {
            this.bankcardAuthRecord = bankcardAuthRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
