import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISiteAuthRecord } from 'app/shared/model/site-auth-record.model';
import { SiteAuthRecordService } from './site-auth-record.service';

@Component({
    selector: 'jhi-site-auth-record-delete-dialog',
    templateUrl: './site-auth-record-delete-dialog.component.html'
})
export class SiteAuthRecordDeleteDialogComponent {
    siteAuthRecord: ISiteAuthRecord;

    constructor(
        protected siteAuthRecordService: SiteAuthRecordService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.siteAuthRecordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'siteAuthRecordListModification',
                content: 'Deleted an siteAuthRecord'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-site-auth-record-delete-popup',
    template: ''
})
export class SiteAuthRecordDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ siteAuthRecord }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SiteAuthRecordDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.siteAuthRecord = siteAuthRecord;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
