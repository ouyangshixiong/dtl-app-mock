/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DtlAppMockTestModule } from '../../../test.module';
import { RealnameInfoDeleteDialogComponent } from 'app/entities/realname-info/realname-info-delete-dialog.component';
import { RealnameInfoService } from 'app/entities/realname-info/realname-info.service';

describe('Component Tests', () => {
    describe('RealnameInfo Management Delete Component', () => {
        let comp: RealnameInfoDeleteDialogComponent;
        let fixture: ComponentFixture<RealnameInfoDeleteDialogComponent>;
        let service: RealnameInfoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [RealnameInfoDeleteDialogComponent]
            })
                .overrideTemplate(RealnameInfoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RealnameInfoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RealnameInfoService);
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
