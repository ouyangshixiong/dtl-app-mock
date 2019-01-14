import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnterpriseAuthRecord } from 'app/shared/model/enterprise-auth-record.model';
import { EnterpriseAuthRecordService } from './enterprise-auth-record.service';

@Component({
    selector: 'jhi-enterprise-auth-record-delete-dialog',
    templateUrl: './enterprise-auth-record-delete-dialog.component.html'
})
export class EnterpriseAuthRecordDeleteDialogComponent {
    enterpriseAuthRecord: IEnterpriseAuthRecord;

    constructor(
        protected enterpriseAuthRecordService: EnterpriseAuthRecordService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.enterpriseAuthRecordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'enterpriseAuthRecordListModification',
                content: 'Deleted an enterpriseAuthRecord'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-enterprise-auth-record-delete-popup',
    template: ''
})
export class EnterpriseAuthRecordDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ enterpriseAuthRecord }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EnterpriseAuthRecordDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.enterpriseAuthRecord = enterpriseAuthRecord;
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
