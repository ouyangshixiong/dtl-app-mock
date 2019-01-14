import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStoreAuthRecord } from 'app/shared/model/store-auth-record.model';

@Component({
    selector: 'jhi-store-auth-record-detail',
    templateUrl: './store-auth-record-detail.component.html'
})
export class StoreAuthRecordDetailComponent implements OnInit {
    storeAuthRecord: IStoreAuthRecord;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ storeAuthRecord }) => {
            this.storeAuthRecord = storeAuthRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
