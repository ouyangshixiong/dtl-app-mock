import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRealnameAuthRecord } from 'app/shared/model/realname-auth-record.model';
import { RealnameAuthRecordService } from './realname-auth-record.service';

@Component({
    selector: 'jhi-realname-auth-record-delete-dialog',
    templateUrl: './realname-auth-record-delete-dialog.component.html'
})
export class RealnameAuthRecordDeleteDialogComponent {
    realnameAuthRecord: IRealnameAuthRecord;

    constructor(
        protected realnameAuthRecordService: RealnameAuthRecordService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.realnameAuthRecordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'realnameAuthRecordListModification',
                content: 'Deleted an realnameAuthRecord'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-realname-auth-record-delete-popup',
    template: ''
})
export class RealnameAuthRecordDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ realnameAuthRecord }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RealnameAuthRecordDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.realnameAuthRecord = realnameAuthRecord;
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
