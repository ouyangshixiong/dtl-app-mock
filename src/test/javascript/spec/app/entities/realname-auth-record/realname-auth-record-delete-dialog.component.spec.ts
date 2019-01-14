/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DtlAppMockTestModule } from '../../../test.module';
import { RealnameAuthRecordDeleteDialogComponent } from 'app/entities/realname-auth-record/realname-auth-record-delete-dialog.component';
import { RealnameAuthRecordService } from 'app/entities/realname-auth-record/realname-auth-record.service';

describe('Component Tests', () => {
    describe('RealnameAuthRecord Management Delete Component', () => {
        let comp: RealnameAuthRecordDeleteDialogComponent;
        let fixture: ComponentFixture<RealnameAuthRecordDeleteDialogComponent>;
        let service: RealnameAuthRecordService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [RealnameAuthRecordDeleteDialogComponent]
            })
                .overrideTemplate(RealnameAuthRecordDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RealnameAuthRecordDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RealnameAuthRecordService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
