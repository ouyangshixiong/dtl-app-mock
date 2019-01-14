import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRealnameAuthRecord } from 'app/shared/model/realname-auth-record.model';

@Component({
    selector: 'jhi-realname-auth-record-detail',
    templateUrl: './realname-auth-record-detail.component.html'
})
export class RealnameAuthRecordDetailComponent implements OnInit {
    realnameAuthRecord: IRealnameAuthRecord;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ realnameAuthRecord }) => {
            this.realnameAuthRecord = realnameAuthRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
