import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISiteAuthRecord } from 'app/shared/model/site-auth-record.model';

@Component({
    selector: 'jhi-site-auth-record-detail',
    templateUrl: './site-auth-record-detail.component.html'
})
export class SiteAuthRecordDetailComponent implements OnInit {
    siteAuthRecord: ISiteAuthRecord;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ siteAuthRecord }) => {
            this.siteAuthRecord = siteAuthRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
